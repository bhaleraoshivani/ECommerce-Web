package com.ecommerce.services.customer.wishlist;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.WishlistDto;
import com.ecommerce.entitiy.Product;
import com.ecommerce.entitiy.User;
import com.ecommerce.entitiy.Wishlist;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl  implements WishlistService{
	
	@Autowired
	private UserRepository  userRepository ;
	
	@Autowired
	private ProductRepository productRepository ;
	
	@Autowired
	private  WishlistRepository wishlistRepository ;
	
	
	public WishlistDto addProductToWishlist(WishlistDto wishlistDto)
	{
		Optional<Product> optionalProduct = productRepository.findById(wishlistDto.getProductId());
		
		Optional<User> optionalUser = userRepository.findById(wishlistDto.getUserId());
		
		if(optionalProduct.isPresent() && optionalUser.isPresent())
		{
			Wishlist wishlist = new Wishlist();
			wishlist.setProduct(optionalProduct.get());
			wishlist.setUser(optionalUser.get());
			
			return wishlistRepository.save(wishlist).getWishlistDto();
			
		}
		return null;
	}
	
	
	public List<WishlistDto> getWishlistByUserId(Long userId)
	{
		return wishlistRepository.findAllByUserId(userId).stream().map(Wishlist:: getWishlistDto).collect(Collectors.toList());
	}
	
	


}
