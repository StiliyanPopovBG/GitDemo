import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BugTest {
    public static void main(String[] args) {

        // Create a bug in Jira via the API
        RestAssured.baseURI = "https://stilianpopov.atlassian.net/";
        String bugName = "Alabala button item are not working - automation Rest Assured";
        String createIssueResponse = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic c3RpbGlhbl9wb3BvdkBhYnYuYmc6QVRBVFQzeEZmR0YwTDdEV25WWVJMdHJGSklyTmJ3SjNhR1dVaDVzR25GdUdOa2JybVNHOEx1SVM0X1VCWTJDR0x0VXc2alpMcVF6YnNtSmRCMk9OTVgyNFdORUNjUHc2Q29ENGZRc1Zpbkx6YS1PLTZ6QWsza3BzMjhtbkpSV18yc09xY3JyU2xoWkd6NUF2TGxXMXBmUkp2aDdqMDRMWnkwQW96S2YzaXBiMTRRM0JrNDF4a1FjPUEyQzVDMkY3")
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"" + bugName + "\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}")
                .log().all()
                .post("rest/api/3/issue")
                .then().log().all().assertThat().statusCode(201)
                .extract()
                .response()
                .asString();

        JsonPath js = new JsonPath(createIssueResponse);
        String issueId = js.getString("id");
        System.out.println(issueId);

        // Get issue after bug creation
        String getResponse = given()
                .pathParam("key", issueId)
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic c3RpbGlhbl9wb3BvdkBhYnYuYmc6QVRBVFQzeEZmR0YwTDdEV25WWVJMdHJGSklyTmJ3SjNhR1dVaDVzR25GdUdOa2JybVNHOEx1SVM0X1VCWTJDR0x0VXc2alpMcVF6YnNtSmRCMk9OTVgyNFdORUNjUHc2Q29ENGZRc1Zpbkx6YS1PLTZ6QWsza3BzMjhtbkpSV18yc09xY3JyU2xoWkd6NUF2TGxXMXBmUkp2aDdqMDRMWnkwQW96S2YzaXBiMTRRM0JrNDF4a1FjPUEyQzVDMkY3")
                .get("rest/api/3/issue/{key}")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath jsonPathGet = new JsonPath(getResponse);
        String actualSummaryName = jsonPathGet.getString("fields.summary");
        System.out.println();
        System.out.println(actualSummaryName);
        System.out.println(bugName);
        System.out.println();
        Assert.assertEquals(actualSummaryName, bugName);

        // Add attachment
        String fileName = "[Screenshot_3.png]";
        String imageName = "Screenshot_3.png";
        String response = given()
                .pathParam("key", issueId)
                .header("Content-Type", "multipart/form-data")
                .header("X-Atlassian-Token", "no-check")
                .header("Authorization", "Basic c3RpbGlhbl9wb3BvdkBhYnYuYmc6QVRBVFQzeEZmR0YwTDdEV25WWVJMdHJGSklyTmJ3SjNhR1dVaDVzR25GdUdOa2JybVNHOEx1SVM0X1VCWTJDR0x0VXc2alpMcVF6YnNtSmRCMk9OTVgyNFdORUNjUHc2Q29ENGZRc1Zpbkx6YS1PLTZ6QWsza3BzMjhtbkpSV18yc09xY3JyU2xoWkd6NUF2TGxXMXBmUkp2aDdqMDRMWnkwQW96S2YzaXBiMTRRM0JrNDF4a1FjPUEyQzVDMkY3")
                .multiPart("file", new File(imageName))
                .post("rest/api/3/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath jsonPathAdd = new JsonPath(response);
        String actualFileName = jsonPathAdd.getString("filename");
        System.out.println();
        System.out.println(actualFileName);
        System.out.println(fileName);
        System.out.println();
        Assert.assertEquals(actualFileName, fileName);


        // Get issue after bug creation
        String fileSize = "44323";
        String getResponseAfterAttachment = given()
                .pathParam("key", issueId)
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic c3RpbGlhbl9wb3BvdkBhYnYuYmc6QVRBVFQzeEZmR0YwTDdEV25WWVJMdHJGSklyTmJ3SjNhR1dVaDVzR25GdUdOa2JybVNHOEx1SVM0X1VCWTJDR0x0VXc2alpMcVF6YnNtSmRCMk9OTVgyNFdORUNjUHc2Q29ENGZRc1Zpbkx6YS1PLTZ6QWsza3BzMjhtbkpSV18yc09xY3JyU2xoWkd6NUF2TGxXMXBmUkp2aDdqMDRMWnkwQW96S2YzaXBiMTRRM0JrNDF4a1FjPUEyQzVDMkY3")
                .get("rest/api/3/issue/{key}")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath jsonPathGetAfterAttachment = new JsonPath(getResponseAfterAttachment);
        String actualFileNameAfterAttachment = jsonPathGetAfterAttachment.getString("fields.attachment[0].filename");
        String actualSizeAfterAttachment = jsonPathGetAfterAttachment.getString("fields.attachment[0].size");
        System.out.println();
        System.out.println(actualFileNameAfterAttachment);
        System.out.println(imageName);
        System.out.println();
        System.out.println();
        System.out.println(actualSizeAfterAttachment);
        System.out.println(fileSize);
        System.out.println();
        Assert.assertEquals(actualFileNameAfterAttachment, imageName);
        Assert.assertEquals(actualSizeAfterAttachment, fileSize);

    }
}
