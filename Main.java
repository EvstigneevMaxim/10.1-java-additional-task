interface ThreePredicate<T, U, V> {
    boolean test(T t, U u, V v);
}

interface ThreeFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

public class Main {
    public static void main(String[] args) {
        ThreePredicate<String, Integer, Boolean> condition = (str, num, flag) -> str.length() > num && flag;
        ThreeFunction<String, Integer, Boolean, String> ifTrue = (str, num, flag) -> str.substring(0, num);
        ThreeFunction<String, Integer, Boolean, String> ifFalse = (str, num, flag) -> str.toUpperCase();

        // Пример 1:
        String result1 = ternaryOperator(condition, ifTrue, ifFalse, "Hello World", 5, true);
        System.out.println(result1);
        // В данном примере, если длина строки "Hello World" больше 5 и значение флага равно true,
        // то будет применена функция ifTrue, которая возвращает подстроку "Hello".
        // В противном случае, применяется функция ifFalse, которая возвращает строку в верхнем регистре "HELLO WORLD".

        // Пример 2:
        String result2 = ternaryOperator(condition, ifTrue, ifFalse, "Hello World", 11, true);
        System.out.println(result2);
        // В этом примере, условие не выполняется, так как длина строки "Hello World" равна 11,
        // а функция ifTrue возвращает подстроку "Hello", если условие выполняется.
        // Таким образом, применяется функция ifFalse, которая возвращает строку в верхнем регистре "HELLO WORLD".

        // Пример 3:
        String result3 = ternaryOperator(condition, ifTrue, ifFalse, "Hello World", 5, false);
        System.out.println(result3);
        // В данном примере, условие не выполняется, так как значение флага равно false,
        // а функция ifTrue возвращает подстроку "Hello", если условие выполняется.
        // Поэтому применяется функция ifFalse, которая возвращает строку в верхнем регистре "HELLO WORLD".
    }

    public static <T, U, V, R> R ternaryOperator(
            ThreePredicate<? super T, ? super U, ? super V> condition,
            ThreeFunction<? super T, ? super U, ? super V, ? extends R> ifTrue,
            ThreeFunction<? super T, ? super U, ? super V, ? extends R> ifFalse,
            T arg1, U arg2, V arg3) {
        if (condition == null || ifTrue == null || ifFalse == null)
            throw new NullPointerException();

        return condition.test(arg1, arg2, arg3)
                ? ifTrue.apply(arg1, arg2, arg3)
                : ifFalse.apply(arg1, arg2, arg3);
    }
}

