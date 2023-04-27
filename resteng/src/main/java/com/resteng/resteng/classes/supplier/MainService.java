package com.resteng.resteng.classes.supplier;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.bankAccount.BankAccount;
import com.resteng.resteng.classes.bankAccount.BankRepo;
import com.resteng.resteng.classes.cart.Cart;
import com.resteng.resteng.classes.cart.CartRepo;
import com.resteng.resteng.classes.cartItem.CartItem;
import com.resteng.resteng.classes.cartItem.CatrItemRepo;
import com.resteng.resteng.classes.cartProduct.CartProductRepo;
import com.resteng.resteng.classes.products.Product;
import com.resteng.resteng.classes.products.ProductRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MainService {

    private SupplierRepository supplierRepository;
    private BankRepo bankRepo;
    private CartRepo cartRepo;
    private CatrItemRepo catrItemRepo;
    private ProductRepo productRepo;

    public MainService(SupplierRepository supplierRepository, BankRepo bankRepo, CartRepo cartRepo,
            CatrItemRepo catrItemRepo, ProductRepo productRepo) {
        this.supplierRepository = supplierRepository;
        this.bankRepo = bankRepo;
        this.cartRepo = cartRepo;
        this.catrItemRepo = catrItemRepo;
        this.productRepo = productRepo;
    }

    List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    Supplier newUser(Supplier supplier) {
        Supplier newUser = supplierRepository.save(supplier);
        Cart cart = cartRepo.save(new Cart());
        newUser.setCart(cart);
        replaceSupplier(newUser, supplier.getSupplier_id());
        return supplier;
    }

    Supplier replaceSupplier(Supplier newSupplier, Long id) {
        Supplier updatedSupplier = supplierRepository.findById(id) //
                .map(supplier -> {
                    supplier.setSupplier_first_name(newSupplier.getSupplier_first_name());
                    supplier.setSupplier_last_name(newSupplier.getSupplier_last_name());
                    supplier.setSupplier_email(newSupplier.getSupplier_email());
                    supplier.setSupplier_country(newSupplier.getSupplier_country());
                    supplier.setSupplier_state(newSupplier.getSupplier_state());
                    supplier.setSupplier_city(newSupplier.getSupplier_city());
                    supplier.setSupplier_address1(newSupplier.getSupplier_address1());
                    supplier.setSupplier_address2(newSupplier.getSupplier_address2());
                    supplier.setSupplier_phone(newSupplier.getSupplier_phone());
                    supplier.setCart(newSupplier.getCart());
                    return supplierRepository.save(supplier);
                }) //
                .orElseGet(() -> {
                    newSupplier.setSupplier_id(id);
                    return supplierRepository.save(newSupplier);
                });
        return updatedSupplier;
    }

    Supplier deleteSupplier(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            if (supplier.getCart() != null) {
                cartRepo.deleteById(supplier.getCart().getId());
            }
            supplierRepository.deleteById(id);
            return supplier;
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    Supplier getSupplierById(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            return supplier;
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    BankAccount getAccountBankOfSupplierById(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            return supplier.getBankAccount();
        } else {
            throw new EntityNotFoundException("supplier with id " + id + " has't bank account");
        }
    }

    private BankAccount createsBankAccount(BankAccount bankAccount) {
        return bankRepo.save(bankAccount);
    }

    BankAccount createsBankAccountOfSupplierById(Long id, BankAccount bankAccount) {
        BankAccount bankAccount2 = createsBankAccount(bankAccount);
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier newSupplier = supplierOptional.get();
            newSupplier.setBankAccount(bankAccount2);
            replaceSupplier(newSupplier, id);
            return newSupplier.getBankAccount();
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    private BankAccount updateBankAccount(Long id, BankAccount bankAccount) {
        BankAccount updatedBankAccount = bankRepo
                .findById(supplierRepository.findById(id).get().getBankAccount().getBank_account_id()) //
                .map(acc -> {
                    acc.setBank_account_balance(bankAccount.getBank_account_balance());
                    acc.setBank_account_number(bankAccount.getBank_account_number());
                    return bankRepo.save(acc);
                }) //
                .orElseGet(() -> {
                    return bankRepo.save(bankAccount);
                });
        return updatedBankAccount;
    }

    void deleteSupplierAccountBank(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier user = supplierOptional.get();
            user.setBankAccount(null);
            replaceSupplier(user, id);
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    List<Product> getSupplierCartProducts(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier user = supplierOptional.get();
            if (user.getCart() != null) {
                Long cartId = user.getCart().getId();
                List<CartItem> cartItems = catrItemRepo.findAll();
                List<Product> fillterCartItems = cartItems.stream()
                        .filter(e -> e.getCart().getId() == cartId)
                        .map(e -> e.getProduct())
                        .collect(Collectors.toList());
                return fillterCartItems;
            } else {
                throw new EntityNotFoundException("Cart for user with id " + id + " is null");
            }
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    private CartItem createsCartItem(CartItem cartItem) {
        return catrItemRepo.save(cartItem);
    }

    Product addSupplierCartProduct(Long id, Long productId) {
        Optional<Supplier> userOptional = supplierRepository.findById(id);
        if (userOptional.isPresent()) {
            Supplier supplier = userOptional.get();
            Product productFound = productRepo.findById(productId).get();

            Cart supplierCart = supplier.getCart();
            CartItem cartItem = new CartItem(productFound, supplierCart);
            createsCartItem(cartItem);

            return productFound;
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    void deleteSupplierCartProduct(Long id, Long productId) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            Cart supplierCart = supplier.getCart();
            List<CartItem> cartItems = catrItemRepo.findAll().stream()
                    .filter(e -> e.getCart().getId() == supplierCart.getId()
                            && e.getProduct().getProduct_id() == productId)
                    .collect(Collectors.toList());
            Long catrItemId = cartItems.get(0).getCart_item_id();
            catrItemRepo.deleteById(catrItemId);
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    Product getSupplierCartProduct(Long id, Long productId) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            Supplier supplier = supplierOptional.get();
            if (supplier.getCart() != null) {
                Long cartId = supplier.getCart().getId();
                List<CartItem> cartItems = catrItemRepo.findAll();
                Product fillterCartItems = cartItems.stream()
                        .filter(e -> e.getCart().getId() == cartId && e.getProduct().getProduct_id() == productId)
                        .map(e -> e.getProduct())
                        .collect(Collectors.toList()).get(0);
                if (fillterCartItems != null)
                    return fillterCartItems;
                else
                    throw new EntityNotFoundException("there is not product with id " + productId);
            } else {
                throw new EntityNotFoundException("Cart for user with id " + id + " is null");
            }
        } else {
            throw new EntityNotFoundException("Supplier with id " + id + " not found");
        }
    }

    public CartProductRepo getUserCartProduct(Long id) {
        return null;
    }

}
