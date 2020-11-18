package suheee.baguniguba.controller;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suheee.baguniguba.domain.Store;
import suheee.baguniguba.dto.account.AccountSignInDTO;
import suheee.baguniguba.dto.common.CommonResDTO;
import suheee.baguniguba.dto.store.*;
import suheee.baguniguba.service.StoreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("baguniguba")
public class StoreController {


    final private StoreService storeService;

    @ApiOperation(value = "상점코드에 대응되는 상점의 정보를 가져옵니다.", notes = "성공시 하나의 상점 정보 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeCode", value = "상점코드", required = true, dataType = "string",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "success",response = StoreResDTO.class)
    })
    @GetMapping("/store")
    public ResponseEntity<CommonResDTO> findStore(@RequestParam("storeCode") String storeCode){
        StoreReqDTO storeReqDTO = new StoreReqDTO(storeCode);
        StoreResDTO storeResDTO = storeService.findStore(storeReqDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(storeResDTO));
    }




    @ApiOperation(value = "계정이 소유한 모든 싱점의 정보를 가져옵니다.", notes = "성공시 상점 목록 반환")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountID", value = "계정 아이디", required = true, dataType = "string",paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "success",response = StoreResDTO.class)
    })
    @GetMapping("/stores")
    public ResponseEntity<CommonResDTO> findAllStores(@RequestParam("accountID") String accountID){
        StoresReqDTO storesReqDTO = new StoresReqDTO(accountID);
        List<StoreResDTO> stores = storeService.findAllStores(storesReqDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(stores));
    }



    @ApiOperation(value = "계정에 상점을 추가합니다.", notes = "성공시 반환 객체 없음")
    @PostMapping("/store/insert")
    public ResponseEntity<CommonResDTO> saveStore(@RequestBody StoreSaveDTO storeSaveDTO){
        System.out.println(storeSaveDTO.getOwnerName());
        storeService.saveStore(storeSaveDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }

    @ApiOperation(value = "상점의 지도 URL 업데이트", notes = "성공시 반환 객체 없음")
    @PutMapping("/store/map/update")
    public ResponseEntity<CommonResDTO> updateStoreMap(@RequestBody StoreMapURLUdateDTO storeMapURLUdateDTO){
        storeService.updateStoreMap(storeMapURLUdateDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }

    @ApiOperation(value = "상점의 지도 마커 업데이트", notes = "성공시 반환 객체 없음")
    @PutMapping("/store/map/marker/update")
    public ResponseEntity<CommonResDTO> updateStoreMapMarker(@RequestBody StoreMapMarkerUpdateDTO storeMapMarkerUpdateDTO){
        storeService.updateStoreMapMarker(storeMapMarkerUpdateDTO);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess());
    }

    @ApiOperation(value = "상점의 지도 마커 가져오기", notes = "성공시 마커 정보 반환")
    @GetMapping("/store/map/marker")
    public ResponseEntity<CommonResDTO> findStoreMapMarker(@RequestParam("storeCode") String storeCode){
        final List<StoreMapMarkerDTO> storeMapMarker = storeService.findStoreMapMarker(storeCode);
        return ResponseEntity.ok().body(CommonResDTO.ofSuccess(storeMapMarker));
    }



}
