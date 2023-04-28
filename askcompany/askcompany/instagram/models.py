from django.db import models


class Post(models.Model):
    message = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self):
        # return f"Custom Post object ({self.id})"
        # return "Custom Post object (%s)" % self.id
        # return "Custom Post object ({})".format(self.id)
        return self.message
