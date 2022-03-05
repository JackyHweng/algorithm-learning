package com.jacky.algorithm.面试编程题集锦;

/**
 * <p>
 * TODO
 * </p>
 *
 * 资料:
 * https://www.jianshu.com/p/ca19c22e02f4
 * @author: HuangJiaJie
 * @create: 2022/2/18
 **/
public class DCL {
    private volatile static DCL instance = null;

    private  DCL(){

    }


    public static  DCL getInstance(){
       if(instance == null) {
           synchronized (DCL.class){
               if(instance == null){
                   return new DCL();
               }
           }
       }
       return instance;
    }

    public static void main(String[] args) {
        for(int i = 0 ; i < 200; i++){
            new Thread(() -> {
                System.out.println(DCL.getInstance());
            }).start();
        }
    }
}
