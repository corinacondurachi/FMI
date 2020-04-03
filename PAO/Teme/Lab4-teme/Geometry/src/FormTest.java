public class FormTest {
    public static void main(String[] args) {
        int numForms = 3;
        Form[] forms = new Form[3];
        forms[0] = new Circle("albastru", 2);
        forms[1] = new Triangle("rosu", 2, 5);
        forms[2] = new Circle("roz", 5);
        for (int i = 0; i < numForms; ++i) {
            Form form = forms[i];
            System.out.print(form + "\n" );
            if (form instanceof Triangle){
                Triangle triangle = (Triangle) form;
                triangle.printTriangleDimensions();
            }
            else if (form instanceof Circle){
                Circle circle = (Circle) form;
                circle.printCircleDimensions();
            }
        }

        for (int i = 0; i < numForms; ++i) {
            forms[i].printDimensions();
        }

    }
}
