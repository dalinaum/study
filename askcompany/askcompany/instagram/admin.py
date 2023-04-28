from django.contrib import admin

from .models import Post


# admin.site.register(Post)
# admin.site.register(Post, PostAdmin)

@admin.register(Post)
class PostAdmin(admin.ModelAdmin):
    list_display = ['id', 'message', 'created_at', 'updated_at']
    list_display_links = ['id', 'message']