package tw.brad.spring4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.brad.spring4.entity.Hotel;
import tw.brad.spring4.repo.HotelRepo;
import tw.brad.spring4.util.JwtToken;

import java.util.List;
import java.util.Map;

@RequestMapping("/hotels")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HotelController {
    @Autowired
    private HotelRepo repo;

    @GetMapping("")
    public ResponseEntity<Map<String,Object>> queryHotel(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int rpp,
            @RequestHeader String Authorization
    ){

        try {
            String subject = JwtToken.parseToken(Authorization.split(" ")[1]);

            Pageable pageable = PageRequest.of(page, rpp);
            Page<Hotel> hotelPage = repo.findAll(pageable);

            Map<String, Object> result = Map.of(
                    "success", true,
                    "data", hotelPage.getContent(),
                    "total", hotelPage.getTotalElements(),
                    "totalPage", hotelPage.getTotalPages(),
                    "page", hotelPage.getNumber(),
                    "isLast", hotelPage.isLast()
            );
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            //throw new RuntimeException(e);

            return ResponseEntity.badRequest().body(Map.of("success",false));

        }
    }

}
