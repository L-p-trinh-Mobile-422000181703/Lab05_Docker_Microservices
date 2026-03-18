from django.urls import path
from tasks.views import index, run_task

urlpatterns = [
    path("", index),
    path("task/", run_task),
]
