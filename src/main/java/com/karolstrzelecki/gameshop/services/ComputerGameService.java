package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.daos.SingleCopyDataDao;
import com.karolstrzelecki.gameshop.dtos.ComputerGameRegisterDto;
import com.karolstrzelecki.gameshop.dtos.CopyAdderDto;
import com.karolstrzelecki.gameshop.dtos.TitleListingValue;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public interface ComputerGameService {

    public void create(ComputerGameRegisterDto cg, MultipartFile[] mp) throws ParseException;

    public List<TitleListingValue> getAllTitles();

    public void addCopy(CopyAdderDto copyAdderDto);

    public List<SingleCopyDataDao> getAllCopies(String gameID);

    public boolean deleteCopy(String copyId);

    public boolean updateCopy(CopyAdderDto copyAdderDto);

    public boolean deleteComputerGame(String cgId);


}
