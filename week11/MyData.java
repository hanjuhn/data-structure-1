package week11;

public class MyData {
    int num;
    String name;
    
    public MyData(int n, String str) {
        num = n;
        name = str;
    }

    @Override
    public String toString() {
        return name+"("+num+")";
    }

    @Override
    public boolean equals(Object that) {
        return (this.num == ((MyData)that).num);
    }
}
