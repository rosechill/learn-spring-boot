package jiwon.financetracker.dto.response;

public record BaseResponse<T>(

        int status,

        String message,

        T data

) {
}