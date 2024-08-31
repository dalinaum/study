class Todo {
  String title;
  String content;
  int active;
  int? id;

  Todo({
    required this.title,
    required this.content,
    required this.active,
    this.id,
  });

  Map<String, dynamic> toMap() => {
        'id': id,
        'title': title,
        'content': content,
        'active': active,
      };
}
