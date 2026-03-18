from django.http import JsonResponse
from .celery_tasks import add

def index(request):
    return JsonResponse({"message": "Django + Celery + Redis"})

def run_task(request):
    result = add.delay(4, 6)
    return JsonResponse({"task_id": str(result.id), "status": "Task submitted"})
