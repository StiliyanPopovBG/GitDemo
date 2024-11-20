import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.CoursePrice());

        // Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        // Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        // Print Title of the first course
        String firstTitle = js.getString("courses[0].title");
        System.out.println(firstTitle);

        // Print All course titles and their respective Prices
        String allTitles = js.getString("courses.title");
        System.out.println(allTitles);

        for (int i = 0; i < count; i++) {
            String allTitlesI = js.getString("courses[" + i + "].title");
//            int allPricesI = js.getInt("courses[" + i + "].price");
            System.out.println(allTitlesI);
            System.out.println(js.getInt("courses[" + i + "].price"));
        }

        // Print no of copies sold by RPA Course
        System.out.println("Print no of copies sold by RPA Course");
        for (int i = 0; i < count; i++) {
            String allTitlesI = js.getString("courses[" + i + "].title");
            if (allTitlesI.equalsIgnoreCase("RPA")) {
                // copies sold
                int desiredCourse = js.getInt("courses[" + i + "].copies");
                System.out.println(desiredCourse);
                break;
            }
        }

        // Verify if Sum of all Course prices matches with Purchase Amount
        System.out.println();
        int totalSum = 0;
        for (int i = 0; i < count; i++) {

            int coursePrice = js.getInt("courses[" + i + "].price");
            System.out.println(coursePrice);
            int courseCopies = js.getInt("courses[" + i + "].copies");
            System.out.println(courseCopies);
            totalSum = coursePrice * courseCopies;
        }
        System.out.println(totalSum);
    }
}
