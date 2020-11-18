package suheee.baguniguba.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import suheee.baguniguba.domain.Account;
import suheee.baguniguba.domain.Product;
import suheee.baguniguba.domain.Store;
import suheee.baguniguba.dto.account.AccountSaveDTO;
import suheee.baguniguba.dto.product.ProductSaveDTO;
import suheee.baguniguba.dto.store.*;
import suheee.baguniguba.enums.ResultCode;
import suheee.baguniguba.exception.business.BusinessException;
import suheee.baguniguba.repository.AccountRepository;
import suheee.baguniguba.repository.StoreRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StoreService {


    final private StoreRepository storeRepository;
    final private AccountRepository accountRepository;

    @Transactional
    public StoreResDTO findStore(final StoreReqDTO storeReqDTO){

        final Store store = storeRepository.findByCode(storeReqDTO.getCode())
                                            .orElseThrow( () -> new BusinessException(ResultCode.ENTITY_NOT_FOUND) );
        return new StoreResDTO(store);
    }

    @Transactional
    public List<StoreResDTO> findAllStores(final StoresReqDTO storesReqDTO){

        final List<Store> stores = storeRepository.findByAccount_Email(storesReqDTO.getAccountID());
        final List<StoreResDTO> result = stores.stream()
                .map(store -> new StoreResDTO(store))
                .collect(Collectors.toList());

        return result;
    }

    @Transactional
    public boolean saveStore(final StoreSaveDTO storeSaveDTO){

        Optional<Store> store = storeRepository.findByNameAndAccount_Email(storeSaveDTO.getName(),storeSaveDTO.getAccountID());
        if (store.isPresent()){
            throw new BusinessException(ResultCode.STORE_DUPLICATION);
        }

        final Account account = accountRepository.findByEmail(storeSaveDTO.getAccountID())
                .orElseThrow(() -> new BusinessException(ResultCode.ACCOUNT_NOT_FOUND));

        Store store1 = storeSaveDTO.toEntity(account);
        store1.SetCode("SMB"+store1.hashCode());
        storeRepository.save(store1);
        return true;
    }

    @Transactional
    public boolean updateStoreMap(final StoreMapURLUdateDTO storeMapURLUdateDTO){
        final Store store = storeRepository.findByCode(storeMapURLUdateDTO.getStoreCode())
                .orElseThrow( () -> new BusinessException(ResultCode.ENTITY_NOT_FOUND) );

        if (!storeMapURLUdateDTO.getUrl().equals("")){
            store.setMapURL(storeMapURLUdateDTO.getUrl());
        }
        return true;
    }

    @Transactional
    public boolean updateStoreMapMarker(final StoreMapMarkerUpdateDTO storeMapMarkerUpdateDTO){

        final Store store = storeRepository.findByCode(storeMapMarkerUpdateDTO.getStoreCode())
                .orElseThrow( () -> new BusinessException(ResultCode.ENTITY_NOT_FOUND) );


        StringBuilder result = new StringBuilder();
        final List<StoreMapMarkerDTO> markers = storeMapMarkerUpdateDTO.getMarkers();

        int i = 0;
        for (StoreMapMarkerDTO marker:  markers) {
            result.append(marker.getX()+(char)0x01+marker.getY()+(char)0x01+marker.getTitle());
            if (i < markers.size()-1){
                result.append((char)0x02);
            }
            i++;
        }
        if (!result.toString().equals("")){
            store.setMapMarkers(result.toString());
            System.out.println(result.toString());
        }
        return true;
    }


    @Transactional
    public List<StoreMapMarkerDTO> findStoreMapMarker(final String storeCode){

        final Store store = storeRepository.findByCode(storeCode)
                .orElseThrow( () -> new BusinessException(ResultCode.ENTITY_NOT_FOUND) );

        String markersString = store.getMapMarkers();

        if (markersString == null || markersString.equals("")){
            throw new BusinessException(ResultCode.ENTITY_NOT_FOUND);
        }

        String[] split1 = markersString.split(Character.toString(0x02));
        if (split1.length <= 0){
            throw new BusinessException(ResultCode.ENTITY_NOT_FOUND);
        }

        List<StoreMapMarkerDTO> list = new ArrayList<>();
        for (int i = 0; i < split1.length; i++) {
            String[] split2 = split1[i].split(Character.toString(0x01));
            if (split2.length != 3){
                throw new BusinessException(ResultCode.ENTITY_NOT_FOUND);
            }
            list.add(new StoreMapMarkerDTO(split2[0],split2[1],split2[2]));
        }
        return list;
    }
}
