package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }


    @ApiOperation(value = "일기를 추가하는 API 입니다", notes = "일기 텍스트와 날씨를 이용해서 DB 에 일기를 저장합니다" )
    //data가 여러 형식으로 보내질 수 있기 때문에 DateTimeFormat으로 형식 지정이 필요
    @PostMapping("/create/diary")
    void createDiary( @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                      @RequestBody String text ){
        diaryService.createDiary(date, text);
    }

    @ApiOperation("일기를 조회하는 API 입니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return diaryService.readDiary(date);
    }


    @ApiOperation("특정 기간 내 일기를 조회하는 API 입니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 첫번째날", example = "2023-06-18")LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 마지막날", example = "2023-06-20") LocalDate endDate){
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("일기를 수정하는 API 입니다")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text){
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("일기를 삭제하는 API 입니다")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        diaryService.deleteDiary(date);
    }
}
