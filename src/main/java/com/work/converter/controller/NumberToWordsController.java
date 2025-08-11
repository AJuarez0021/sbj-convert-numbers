package com.work.converter.controller;

import com.work.converter.dto.ErrorResponseDTO;
import com.work.converter.dto.NumberRequestDTO;
import com.work.converter.dto.NumberResponseDTO;
import com.work.converter.exception.ServiceException;
import com.work.converter.service.NumberToWordsService;
import com.work.converter.util.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class NumberToWordsController.
 *
 * @author ajuar
 */
@RestController
@RequestMapping("/v1/numbers")
@Slf4j
public class NumberToWordsController {

    /**
     * The number to words service.
     */
    private final NumberToWordsService numberToWordsService;

    /**
     * Instantiates a new number to words controller.
     *
     * @param numberToWordsService the number to words service
     */
    public NumberToWordsController(NumberToWordsService numberToWordsService) {
        this.numberToWordsService = numberToWordsService;
    }

    /**
     * Convert.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping(path = "/convert", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "convertNumberToWords", description = "Convert number to words")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation",
                content = @Content(schema = @Schema(implementation = NumberResponseDTO.class)
                )),
        @ApiResponse(responseCode = "400", description = "Invalid data",
                content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)
                )),
        @ApiResponse(responseCode = "500", description = "General error",
                content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)
                ))
    })
    public ResponseEntity<NumberResponseDTO> convertNumberToWords(@Valid @RequestBody NumberRequestDTO request) {
        try {
            log.debug("Request: {}", request);
            String words = numberToWordsService.convertNumberToWords(request.number());
            NumberResponseDTO response = new NumberResponseDTO(
                    request.number(),
                    words,
                    DateUtil.getFormat(LocalDateTime.now())
            );
            log.debug("Response: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while converting the number: " + request.number());
        }
    }
}
