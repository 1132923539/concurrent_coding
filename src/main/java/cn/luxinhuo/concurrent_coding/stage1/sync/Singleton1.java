package cn.luxinhuo.concurrent_coding.stage1.sync;

//懒汉模式（写法偷懒，行为也偷懒）
public class Singleton1 {

    /*
     *使用静态的原因是使得类在被加载时就生成了对象，因此只有一个对象
     * 若不适用static，则需要实例化对象时，才会生成对象
     */
    private static Singleton1 singleton1 = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance() {
        return singleton1;
    }
}
