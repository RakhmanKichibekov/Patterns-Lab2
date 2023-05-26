interface DairyProduct {
    void doSomething();
}

interface Grocery {
    void doSomething();
}

// Конкретные продукты
class Milk implements DairyProduct {
    @Override
    public void doSomething() {
        System.out.println("buy milk");
    }
}

class Yogurt implements DairyProduct {
    @Override
    public void doSomething() {
        System.out.println("buy yogurt");
    }
}

class Bread implements Grocery {
    @Override
    public void doSomething() {
        System.out.println("buy bread");
    }
}

class Croissant implements Grocery {
    @Override
    public void doSomething() {
        System.out.println("buy croissant");
    }
}

// Абстрактная фабрика
interface Shop {
    DairyProduct createDaily();
    Grocery createGroccery();
}

// Конкретные фабрики
class Metro implements Shop {
    @Override
    public DairyProduct createDaily() {
        return new Milk();
    }

    @Override
    public Grocery createGroccery() {
        return new Bread();
    }
}

class Aushan implements Shop {
    @Override
    public DairyProduct createDaily() {
        return new Yogurt();
    }

    @Override
    public Grocery createGroccery() {
        return new Croissant();
    }
}

abstract class DairyDecorator implements DairyProduct {
    protected DairyProduct decoratedProductA;

    public DairyDecorator(DairyProduct decoratedProductA) {
        this.decoratedProductA = decoratedProductA;
    }

    @Override
    public void doSomething() {
        decoratedProductA.doSomething();
    }
}

class DecoratorStrawbery extends DairyDecorator {
    public DecoratorStrawbery(DairyProduct decoratedProductA) {
        super(decoratedProductA);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        System.out.println("Молоко со вкусом клубники");
    }
}

abstract class GroceryDecorator implements Grocery {
    protected Grocery decoratedProductB;

    public GroceryDecorator(Grocery decoratedProductB) {
        this.decoratedProductB = decoratedProductB;
    }

    @Override
    public void doSomething() {
        decoratedProductB.doSomething();
    }
}

class ProductBDecorator1 extends GroceryDecorator {
    public ProductBDecorator1(Grocery decoratedProductB) {
        super(decoratedProductB);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        System.out.println("Хлеб с кунжутом");
    }
}

// Клиентский код
class Client {
    private DairyProduct dairyProduct;
    private Grocery grocery;

    public Client(Shop factory) {
        dairyProduct = factory.createDaily();
        grocery = factory.createGroccery();
    }

    public void execute() {
        // Декорирование продуктов
        DairyProduct decoratedProductA = new DecoratorStrawbery(dairyProduct);
        Grocery decoratedProductB = new ProductBDecorator1(grocery);

        decoratedProductA.doSomething();
        decoratedProductB.doSomething();
    }

    public static void main(String[] args) {
        // Использование фабрики 1
        Shop factory1 = new Metro();
        Client client1 = new Client(factory1);
        client1.execute();

        // Использование фабрики 2
        Shop factory2 = new Aushan();
        Client client2 = new Client(factory2);
        client2.execute();
    }
}