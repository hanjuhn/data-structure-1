package week10;

public class MyData {
    int num;
    String name;
    
    MyData(int n, String str) {
        num = n;
        name = str;
    }

    @Override
    public String toString() {
        return name+"("+num+")";
    }

    // 두 MyData 객체를 비교할 때 num 값만 기준으로 비교
    @Override
    public boolean equals(Object that) {
        return (this.num == ((MyData)that).num);
    }
}
