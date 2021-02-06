package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.daos.SingleCopyDataDao;
import com.karolstrzelecki.gameshop.dtos.ComputerGameRegisterDto;
import com.karolstrzelecki.gameshop.dtos.CopyAdderDto;
import com.karolstrzelecki.gameshop.dtos.DateHelper;
import com.karolstrzelecki.gameshop.dtos.TitleListingValue;
import com.karolstrzelecki.gameshop.models.ComputerGame;
import com.karolstrzelecki.gameshop.models.Copy.CaseCopy;
import com.karolstrzelecki.gameshop.models.Copy.Conditions;
import com.karolstrzelecki.gameshop.models.Copy.Copy;
import com.karolstrzelecki.gameshop.models.Copy.Platform;
import com.karolstrzelecki.gameshop.models.GameCategory;
import com.karolstrzelecki.gameshop.repository.ComputerGameRepository;
import com.karolstrzelecki.gameshop.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComputerGameServiceImpl implements ComputerGameService{

    @Autowired
    ComputerGameRepository repo;

    @Autowired
    CopyRepository copyRepository;

    @Autowired
    ImageService imageService;

    @Override
    public void create(ComputerGameRegisterDto cg, MultipartFile[] mp)  {

        ComputerGame cgi = new ComputerGame();
        cgi.setTitle(cg.getTitle());
        cgi.setAlternativeTitle(cg.getAlternativeTitle());

        List<String> catNames = cg.getCategories();
//        Set<GameCategory> cats = catNames.stream().map(e-> GameCategory.valueOf(e)).collect(Collectors.toSet());
        Set<GameCategory> cats = catNames.stream().map(e-> GameCategory.get(e)).collect(Collectors.toSet());

        cgi.setCategories(cats);
        cgi.setDescription(cg.getDescription());
        cgi.setMaxNumberOfPlayers(cg.getMaxNumberOfPlayers());
        cgi.setPlayersRating(cg.getPlayersRating());
        cgi.setYtLink(cg.getYtLink());
        DateHelper tmpDate = cg.getReleaseDate();
        String dateFormat= tmpDate.day+"/"+tmpDate.month+"/"+tmpDate.year;
        Date date1 = null;
        try {
             date1=new SimpleDateFormat("dd/MM/yyyy").parse(dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cgi.setReleaseDate(date1);

        List<String> fileNames = Arrays.asList(mp).stream().
                map(file -> imageService.save(file)).collect(Collectors.toList());

        System.out.println(fileNames);
        cgi.setImageUrl(fileNames);

        repo.save(cgi);


    }

    @Override
    public List<TitleListingValue> getAllTitles() {
        List<ComputerGame> cg = repo.findAll();
        List<TitleListingValue> titleListingValues = cg.stream().map(e->{
            TitleListingValue val = new TitleListingValue();
            val.game_id = e.getComputerGameId();
            val.game_title = e.getTitle();
            List<Copy> tmpList = e.getCopies();
            List<SingleCopyDataDao> copies = new ArrayList<>();
            val.quantity = tmpList.size();
            System.out.println(val.quantity);
            copies = tmpList.stream().map(f->{
                SingleCopyDataDao singleCopyDataDao = new SingleCopyDataDao();
                singleCopyDataDao.copy_id = f.getCopyId();

                singleCopyDataDao.condition = Conditions.brandnew.name();
                singleCopyDataDao.platform = f.getPlatform().getName();
                singleCopyDataDao.price = String.valueOf(f.getPrice().doubleValue());
                return singleCopyDataDao;

            }).collect(Collectors.toList());
            val.copies = copies;


            return val;
        }).collect(Collectors.toList());


        return titleListingValues;
    }


    @Override
    public void addCopy(CopyAdderDto copyAdderDto) {
        System.out.println(copyAdderDto);
        Long val = Long.valueOf(copyAdderDto.game_id);
        Optional<ComputerGame> cg = repo.findById(val);
        if(cg.isPresent()) {
            ComputerGame existingCg = cg.get();
            CaseCopy caseCopy = new CaseCopy();
            caseCopy.setConditions(Conditions.get(copyAdderDto.condition));
            caseCopy.setPlatform(Platform.get(copyAdderDto.platform));
            String value = copyAdderDto.price;
            BigDecimal money = new BigDecimal(value.replaceAll(",", ""));
            caseCopy.setPrice(money);
           existingCg.addCopy(caseCopy);
           repo.save(existingCg);

        } else {
            System.out.println("brak tytułu w bazie danych");
        }


    }


    @Override
    public List<SingleCopyDataDao> getAllCopies(String gameID) {
        Long val = Long.valueOf(gameID);
        Optional<ComputerGame> cg = repo.findById(val);
        List<SingleCopyDataDao> copies = new ArrayList<>();
        if(cg.isPresent()){
            ComputerGame existingCg = cg.get();
            Long currentGameId = existingCg.getComputerGameId();
            List<Copy> cp = existingCg.getCopies();
            copies = cp.stream().filter(e->
              Objects.isNull(e.getShoppingCart())
            ).map(e->{
                SingleCopyDataDao singleCopyDataDao = new SingleCopyDataDao();
                singleCopyDataDao.copy_id = e.getCopyId();
//                singleCopyDataDao.game_id = currentGameId;
                //
                singleCopyDataDao.condition = Conditions.brandnew.name();
                        //e.getPlatform().getName();

                singleCopyDataDao.platform = e.getPlatform().getName();
                singleCopyDataDao.price = String.valueOf(e.getPrice().doubleValue());
                return singleCopyDataDao;

            }).collect(Collectors.toList());


        }else{
            System.out.println("brak takie gry w bazie danych");
        }


        return copies;
    }

    @Override
    public boolean deleteCopy(String copyId) {
        Long val = Long.valueOf(copyId);
        Optional<Copy> cp=copyRepository.findById(val);
        if(cp.isPresent()){
            Copy copy = cp.get();
            ComputerGame cg = copy.getComputerGame();
            cg.removeCopy(copy);
            repo.save(cg);
            return true;
        }return false;


//        Optional<Copy> cp= copyRepository.findById(val);
//        if(cp.isPresent()){
//            Copy deletedCopy = cp.get();
//            copyRepository.delete(deletedCopy);
//            return true;
//        }else return false;

    }

    @Override
    public boolean deleteComputerGame(String cgId) {
        Long val = Long.valueOf(cgId);
        System.out.println(val);
        Optional<ComputerGame> ocg = repo.findById(val);
        if(ocg.isPresent()){
            ComputerGame computerGame = ocg.get();
            repo.delete(computerGame);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCopy(CopyAdderDto copyAdderDto) {
        System.out.println(copyAdderDto);
        Long val= Long.valueOf(copyAdderDto.copy_id);

        Optional<Copy> oc = copyRepository.findById(val);
        if(oc.isPresent()){
            Copy copy = oc.get();
            String value = copyAdderDto.price;
            copy.setPlatform(Platform.get(copyAdderDto.platform));
            BigDecimal money = new BigDecimal(value.replaceAll(",", ""));
            copy.setPrice(money);
            copyRepository.save(copy);
        }

        return false;
    }
}
