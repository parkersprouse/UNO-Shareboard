package com.bayou.managers.impl;

import com.bayou.converters.ImageConverter;
import com.bayou.domains.Image;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.ImageResourceAccessor;
import com.bayou.views.ImageInfoView;
import com.bayou.views.ImageView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rachel on 3/19/2017.
 */
@Service
public class ImageManager implements IManager<ImageView> {
    @Autowired
    private ImageResourceAccessor imageRas;

    @Autowired
    private ImageConverter imageConverter;

    @Override
    public ImageView get(Long id) throws NotFoundException {
        ImageView imageView;
        Image image = imageRas.find(id);

        if (image == null) {
            throw new NotFoundException(String.valueOf(id));
        } else {
            imageView = imageConverter.convertToView(image);
        }

        return imageView;
    }

    public ImageInfoView getInfo(Long id) throws NotFoundException {
        return imageConverter.convertToInfoView(this.get(id));
    }

    public List<ImageInfoView> findByOwner(Long id) throws NotFoundException {
        List<ImageInfoView> images = new ArrayList<ImageInfoView>();
        for(Image i : imageRas.findByOwner(id)) {
            images.add(imageConverter.convertToInfoView(imageConverter.convertToView(i)));
        }
        return images;
    }

    @Override
    public List<ImageView> getAll() throws NotFoundException {
        return null;
    }

    @Override
    public Long add(ImageView imageView) {
        Long id = -1L;
        try {
            id = imageRas.add(imageConverter.convertToDomain(imageView));
        } catch (DataIntegrityViolationException e) {
            System.err.println("Image: " + imageView.getId() + " already exists");
        }

        return id;
    }

    @Override
    public Long update(ImageView view) {
        Image image = imageConverter.convertToDomain(view);    //converts the image info view to the user domain Object
        if (view.getId() == null) {   //triggers a no content if the id is null
            return -1L;
        }

        Image retrievedImage = imageRas.find(view.getId());    //get the image we are updating

        if (retrievedImage == null) {    //if the requested image doesn't exist
            throw new javax.ws.rs.NotFoundException();
        }

        image.setVersion(retrievedImage.getVersion());   //gets the record's we are updating version number

        image = imageConverter.updateConversion(image, retrievedImage); //adds values to any null properties that were not sent in the request on a partial update

        return imageRas.update(image);
    }

    @Override
    public void delete(Long id) {
        try {
            imageRas.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("The image with ID:" + id + " does not exist in the database");
        }
    }
}
