package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    private final Object target;

    public CustomInvocationHandler(SomeInterfaceWithMethods target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        System.out.println(methodName + " in");
        Object result = method.invoke(target, args);
        System.out.println(methodName + " out");
        return result;
    }

}
