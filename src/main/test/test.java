interface Aplph {
    void f();
}

class Be implements Aplph {

    @Override
    public void f() {
        System.out.println("Be");
    }

    public void g(int i) {
        i++;
    }
}

public class test {
    public static void main(String[] args) {
        int i = 13 & 17;
        System.out.println(i);
    }
}

