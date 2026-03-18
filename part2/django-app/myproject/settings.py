import os

SECRET_KEY = "django-secret-key-for-dev"
DEBUG = True
ALLOWED_HOSTS = ["*"]

INSTALLED_APPS = [
    "django.contrib.contenttypes",
    "django.contrib.staticfiles",
    "tasks",
]

ROOT_URLCONF = "myproject.urls"
CELERY_BROKER_URL = os.environ.get("CELERY_BROKER_URL", "redis://redis-django:6379/0")
