package com.resteng.resteng.classes.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.resteng.resteng.classes.bankAccount.BankAccount;
import com.resteng.resteng.classes.bankAccount.BankRepo;
import com.resteng.resteng.classes.cart.Cart;
import com.resteng.resteng.classes.cart.CartRepo;
import com.resteng.resteng.classes.mainUser.MainUserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MainService {

    private UserRepository userRepository;
    private BankRepo bankRepo;
    private CartRepo cartRepo;
    private MainUserRepo mainUserRepo;

    public List<AppUser> getAllUser() {
        return userRepository.findAll();
    }

    public AppUser newUser(AppUser user) {
        Cart cart = cartRepo.save(new Cart());
        user.setCart(cart);
        mainUserRepo.save(user.getMainUser());
        AppUser newUser = userRepository.save(user);
        return newUser;
    }

    AppUser replaceUser(AppUser newUser, Long id) {
        AppUser updatedUser = userRepository.findById(id) //
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

    AppUser getUserById(Long id) {
        Optional<AppUser> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            return user;
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    BankAccount getAccountBankOfUserById(Long id) {
        Optional<AppUser> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
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
        Optional<AppUser> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            AppUser newUser = userOptional.get();
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

    BankAccount updateBankAccountOfUserById(Long id, BankAccount account) {
        BankAccount account2 = updateBankAccount(id, account);
        Optional<AppUser> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            user.setBankAccount(account2);
            replaceUser(user, id);
            return account;
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    void deleteUserAccountBank(Long id) {
        Optional<AppUser> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            user.setBankAccount(null);
            replaceUser(user, id);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

}
