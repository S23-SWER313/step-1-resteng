package com.resteng.resteng.classes.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.resteng.resteng.classes.account.Account;
import com.resteng.resteng.classes.account.AccountRep;
import com.resteng.resteng.classes.bankAccount.BankAccount;
import com.resteng.resteng.classes.bankAccount.BankRepo;
import com.resteng.resteng.classes.cart.Cart;
import com.resteng.resteng.classes.cart.CartRepo;
import com.resteng.resteng.classes.cartItem.CartItem;
import com.resteng.resteng.classes.cartItem.CatrItemRepo;
import com.resteng.resteng.classes.products.Product;
import com.resteng.resteng.classes.products.ProductRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MainService {

    private UserRepository userRepository;
    private AccountRep accountRep;
    private BankRepo bankRepo;
    private CartRepo cartRepo;
    private CatrItemRepo catrItemRepo;
    private ProductRepo productRepo;


    public MainService(UserRepository userRepository, AccountRep accountRep, BankRepo bankRepo, CartRepo cartRepo,
            CatrItemRepo catrItemRepo, ProductRepo productRepo) {
        this.userRepository = userRepository;
        this.accountRep = accountRep;
        this.bankRepo = bankRepo;
        this.cartRepo = cartRepo;
        this.catrItemRepo = catrItemRepo;
        this.productRepo = productRepo;
    }

    List<User> getAllUser() {
        return userRepository.findAll();
    }

    User newUser(User user) {
        User newUser = userRepository.save(user);
        Cart cart = cartRepo.save(new Cart());
        newUser.setCart(cart);
        replaceUser(newUser, user.getUser_id());
        return user;
    }

    User replaceUser(User newUser, Long id) {
        User updatedUser = userRepository.findById(id) //
                .map(user -> {
                    user.setUser_first_name(newUser.getUser_first_name());
                    user.setUser_last_name(newUser.getUser_last_name());
                    user.setUser_email(newUser.getUser_email());
                    user.setUser_country(newUser.getUser_country());
                    user.setUser_state(newUser.getUser_state());
                    user.setUser_city(newUser.getUser_city());
                    user.setUser_address1(newUser.getUser_address1());
                    user.setUser_address2(newUser.getUser_address2());
                    user.setUser_phone(newUser.getUser_phone());
                    user.setCart(newUser.getCart());
                    return userRepository.save(user);
                }) //
                .orElseGet(() -> {
                    newUser.setUser_id(id);
                    return userRepository.save(newUser);
                });
        return updatedUser;
    }

    User deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getCart() != null) {
                cartRepo.deleteById(user.getCart().getId());
            }
            userRepository.deleteById(id);
            return user;
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user;
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    Account getAccountOfUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getAccounts();
        } else {
            throw new EntityNotFoundException("User with id " + id + " has't account");
        }
    }

    private Account createsAccount(Account account) {
        return accountRep.save(account);
    }

    Account createsAccountOfUserById(Long id, Account account) {
        Account account2 = createsAccount(account);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setAccounts(account2);
            replaceUser(user, id);
            return user.getAccounts();
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    private Account updateAccount(Long id, Account account) {
        Account updatedAccount = accountRep.findById(userRepository.findById(id).get().getAccounts().getAccount_id()) //
                .map(acc -> {
                    acc.setAccount_password(account.getAccount_password());
                    acc.setAccount_email(account.getAccount_email());
                    acc.setAccount_role(account.getAccount_role());
                    return accountRep.save(acc);
                }) //
                .orElseGet(() -> {
                    return accountRep.save(account);
                });
        return updatedAccount;
    }

    Account updateAccountOfUserById(Long id, Account account) {
        Account account2 = updateAccount(id, account);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setAccounts(account2);
            replaceUser(user, id);
            return user.getAccounts();
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    BankAccount getAccountBankOfUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getBankAccount();
        } else {
            throw new EntityNotFoundException("User with id " + id + " has't bank account");
        }
    }

    private BankAccount createsBankAccount(BankAccount bankAccount) {
        return bankRepo.save(bankAccount);
    }

    BankAccount createsBankAccountOfUserById(Long id, BankAccount bankAccount) {
        BankAccount bankAccount2 = createsBankAccount(bankAccount);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User newUser = userOptional.get();
            newUser.setBankAccount(bankAccount2);
            replaceUser(newUser, id);
            return newUser.getBankAccount();
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    private BankAccount updateBankAccount(Long id, BankAccount bankAccount) {
        BankAccount updatedBankAccount = bankRepo
                .findById(userRepository.findById(id).get().getBankAccount().getBank_account_id()) //
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

    Account updateBankAccountOfUserById(Long id, BankAccount account) {
        BankAccount account2 = updateBankAccount(id, account);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBankAccount(account2);
            replaceUser(user, id);
            return user.getAccounts();
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    void deleteUserAccountBank(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setBankAccount(null);
            replaceUser(user, id);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    List<Product> getUserCartProducts(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
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
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    private CartItem createsCartItem(CartItem cartItem) {
        return catrItemRepo.save(cartItem);
    }

    Product addUserCartProduct(Long id, Long productId) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Product productFound = productRepo.findById(productId).get();

            Cart userCart = user.getCart();
            CartItem cartItem = new CartItem(productFound, userCart);
            createsCartItem(cartItem);

            return productFound;
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    void deleteUserCartProduct(Long id, Long productId) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Cart userCart = user.getCart();
            List<CartItem> cartItems = catrItemRepo.findAll().stream()
                    .filter(e -> e.getCart().getId() == userCart.getId() && e.getProduct().getProduct_id() == productId)
                    .collect(Collectors.toList());
            Long catrItemId = cartItems.get(0).getCart_item_id();
            catrItemRepo.deleteById(catrItemId);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    Product getUserCartProduct(Long id, Long productId) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getCart() != null) {
                Long cartId = user.getCart().getId();
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
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

}
