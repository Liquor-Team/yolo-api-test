package liquorteam.yoloapitest.result;

import liquorteam.yoloapitest.liquor.Liquor;
import liquorteam.yoloapitest.message.ApiResponse;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class ResultRecorder {

    private int resultCnt = 0;
    private final String path;
    private final FileWriter file;

    public ResultRecorder() throws IOException {
        this.path = System.getProperty("user.dir");
        String fileName = "/output.txt";
        file = new FileWriter(path + fileName, true);
    }

    public void recordSuccessResult(ApiResponse apiResponse) throws IOException {
        resultCnt += 1;
        String result = "Success " + resultCnt + " api call !! \n";
        file.write(result);
        System.out.println(result);
        String introduce = "the Result is ";
        file.write(introduce);
        System.out.print(introduce);
        Liquor[] liquors = apiResponse.getLiquors();
        for(Liquor liquor : liquors) {
            file.write(liquor.getName());
            System.out.print(liquor.getName());
            file.write(" ");
            System.out.print(" ");
        }
        file.write("\n");
        System.out.println();
    }

    public void record400FailedResult() throws IOException {
        resultCnt += 1;
        String result = "Failed " + resultCnt + " api call !! \n";
        file.write(result);
        System.out.println(result);
    }

    public void record500FailedResult() throws IOException {
        String result = "Server has error \n";
        file.write(result);
        System.out.println(result);
    }

    public void destroyFileConnector() throws IOException {
        file.close();
        System.out.println("Successively disconnet!!");
    }
}
