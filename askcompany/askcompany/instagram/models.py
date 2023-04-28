from django.db import models


class Post(models.Model):
    message = models.TextField()
    is_public = models.BooleanField(default=False, verbose_name='공개여부')
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.message

    # def message_length(self):
    #     return len(self.message)
    #
    # message_length.short_description = "메시지 글자수"
    #@property로 함수를 쓸 수 있지만 그 경우에는 short_description 어트리뷰트를 사용할 수 없다.