package liquorteam.yoloapitest.result;

import liquorteam.yoloapitest.dto.Liquor;
import liquorteam.yoloapitest.dto.YoloApiResponseDto;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class YoloApiCallResultRecorder implements ResultRecorder {

    private int resultCnt = 0;
    private final String path;
    private final FileWriter file;

    public YoloApiCallResultRecorder() throws IOException {
        this.path = System.getProperty("user.dir");
        String fileName = "/output.txt";
        file = new FileWriter(path + fileName, true);
    }


    public void recordSuccessResult(YoloApiResponseDto yoloApiResponseDto) throws IOException {
        resultCnt += 1;
        LocalDateTime nowTime = LocalDateTime.now();

        String result = "########### Api Call Count : " + resultCnt + " ###########\n";
        file.write(result);
        System.out.print(result);

        String timeInform = "[Timestamp] ";
        String formatedNow = nowTime.format(DateTimeFormatter.ofPattern("(yyyy / MM / dd)  HH : mm : ss\n"));
        file.write(timeInform);
        file.write(formatedNow);
        System.out.print(timeInform);
        System.out.print(formatedNow);

        String introduce = "[Detected Liquors] ";
        file.write(introduce);
        System.out.print(introduce);
        Liquor[] liquors = yoloApiResponseDto.getLiquors();
        for(Liquor liquor : liquors) {
            file.write(liquor.getName());
            System.out.print(liquor.getName());
            file.write(" ");
            System.out.print(" ");
        }
        file.write("\n");
        System.out.println();
    }

    @Override
    public void record400FailedResult() throws IOException {
        resultCnt += 1;
        String result = "########### Api Call Count : " + resultCnt + " ###########\n";
        file.write(result);
        System.out.print(result);

        String errorCause = "[Error Http Status Code] 4xx \n";
        file.write(errorCause);
        System.out.print(errorCause);
        //destroyFileConnector();
        //System.exit(0);
    }

    @Override
    public void record500FailedResult(String statusCode) throws IOException {
        resultCnt += 1;
        String result = "########### Api Call Count : " + resultCnt + " ###########\n";
        file.write(result);
        System.out.print(result);

        String errorCause = "[Error Http Status Code] " + statusCode + " \n";
        file.write(errorCause);
        System.out.print(errorCause);
        //destroyFileConnector();
        //System.exit(0);
    }

    @Override
    public void destroyFileConnector() throws IOException {
        file.close();
        System.out.println("########### Successively disconnet!! ###########");
    }
}
