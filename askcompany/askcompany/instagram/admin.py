from django.contrib import admin

from .models import Post


@admin.register(Post)
class PostAdmin(admin.ModelAdmin):
    list_display = ['id', 'message', 'message_length', 'created_at', 'updated_at']
    list_display_links = ['id', 'message']
    search_fields = ['message']

    @staticmethod
    def message_length(post):
        return f"{len(post.message)} 글자"

# >>> from instagram.models import Post
# >>> Post.objects.all()
# <QuerySet [<Post: 첫번째 메시지>, <Post: 두번째 메시지>, <Post: 세번째 메시지>]>
# >>> Post.objects.all().filter(message__icontains='첫번째')
# <QuerySet [<Post: 첫번째 메시지>]>
# >>> qs =Post.objects.all().filter(message__icontains='첫번째')
# >>> print(qs.query)
# SELECT "instagram_post"."id", "instagram_post"."message", "instagram_post"."created_at", "instagram_post"."updated_at" FROM "instagram_post" WHERE "instagram_post"."message" LIKE %첫번째% ESCAPE '\'
