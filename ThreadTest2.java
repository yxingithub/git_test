/**
 * @author banjuer
 * 该类用于测试StringBuilder为线程不安全的
 * --思路:
 *   要达到验证齐为线程不安全,应该通过多个线程调用同一个对象方法.
 * --实现:
 *   1.创建一个StringBuilder对象sb传入"0000011111"
 *   2.多个线程同时调用sb对象的reverse
 *   3.当结果出现01混合状态时,则验证了线程不安全
 */
public class ThreadTest2{ 

    public static void main(String[] args) {

        StringBuilder sb=new StringBuilder("0000011111");

        //循环创建多个线程
        for(int i=0;i<100;i++){
            new Thread(new MyThread(sb)).start();
        }

    }

    //写一个线程类
    //类为静态的,因为创建对象是在静态的主方法中
    private static class MyThread implements Runnable{
        //定义成员属性
        private StringBuilder sb;
        //传入参数
        private MyThread(StringBuilder sb){
            this.sb=sb;
        }

        public void run() {
            //这一步循环,可以增加出现线程不安全的该路
            //循环调用sb的reverse()
            for(int i=0;i<100;i++){             
                sb.reverse();
            }
            //输出sb字符串(此处默认调用了toString())
            System.out.println(sb);
        }

    }

}
