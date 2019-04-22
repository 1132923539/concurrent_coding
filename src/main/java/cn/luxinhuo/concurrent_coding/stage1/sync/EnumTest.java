package cn.luxinhuo.concurrent_coding.stage1.sync;

public class EnumTest {
    public static void main(String[] args) {
        System.out.println(enumTest.BB.getAnno());
    }

    private enum enumTest{
        AA("aa"),BB("bb");

        private String anno;

        enumTest(String anno) {
            this.anno = anno;
        }

        public String getAnno(){
            return anno;
        }
    }
}
