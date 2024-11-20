package files;

public class payload {
    public static String AddPlace() {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -55.383494,\n" +
                "    \"lng\": 55.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Backyard house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, Detelina\",\n" +
                "  \"types\": [\n" +
                "    \"running shoes\",\n" +
                "    \"beer shop\"\n" +
                "  ],\n" +
                "  \"website\": \"https://rahulshettyacademy.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }

    public static String CoursePrice() {
        return "{\n" +
                "\t\"dashboard\": {\n" +
                "\t\"purchaseAmount\":1162,\n" +
                "\t\"website\":\"rahulshettyacademy.com\"\n" +
                "\t},\n" +
                "\t\t\"courses\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"title\":\"Selenium Python\",\n" +
                "\t\t\t\"price\":50,\n" +
                "\t\t\t\"copies\":6\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\":\"Cypress\",\n" +
                "\t\t\t\"price\":40,\n" +
                "\t\t\t\"copies\":4\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\":\"RPA\",\n" +
                "\t\t\t\"price\":45,\n" +
                "\t\t\t\"copies\":10\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\":\"Appium\",\n" +
                "\t\t\t\"price\":36,\n" +
                "\t\t\t\"copies\":7\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }

    public static String AddBook() {
        String payload = "{\n" +
                "    \"name\":\"Learn Appium Automation with Java + ABC\",\n" +
                "    \"isbn\":\"bcdaaa1\",\n" +
                "    \"aisle\":\"227\",\n" +
                "    \"author\":\"John Doe\"\n" +
                "}";
        return payload;
    }

    public static String AddBookDynamic(String isbn, String aisle) {
        return "{\n" +
                "    \"name\":\"Learn Appium Automation with Java + ABC\",\n" +
                "    \"isbn\":\"" + isbn + "\",\n" +
                "    \"aisle\":\"" + aisle + "\",\n" +
                "    \"author\":\"John Doe\"\n" +
                "}";
    }
}
