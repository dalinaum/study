class Person {
  String name;
  double height;
  double weight;
  double bmi;

  Person(this.name, this.height, this.weight)
      : bmi = weight / (height / 100) * (height / 100);
}
