void main() {
  Car bmw = Car(320, 100000, 'BMW');
  Car benz = Car(250, 70000, 'BENZ');
  Car ford = Car(200, 80000, 'FORD');
  
  bmw.saleCar();
  bmw.saleCar();
  bmw.saleCar();
  print(bmw.price);
}

class Car {
  int maxSpeed;
  num price;
  String name;
  
  Car(this.maxSpeed, this.price, this.name);
  
  num saleCar() {
    price = price * 0.9;
    return price;
  }
}
