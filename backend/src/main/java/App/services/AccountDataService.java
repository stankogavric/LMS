package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import App.models.AccountData;
import App.repositories.AccountDataRepository;

@Service
public class AccountDataService {

    @Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    private AccountDataRepository accountDataRepo;

    public AccountDataService() {
    }

    public Iterable<AccountData> getAccountData() {
        return accountDataRepo.findAll();
    }

    public Optional<AccountData> getAccountDataById(Long id) {
        return accountDataRepo.findById(id);
    }
    
    public Optional<AccountData> getAccountDataByUserName(String username) {
        return accountDataRepo.findByUsername(username);
    }

    public void addAccountData(AccountData accountData) {
    	accountData.setPassword(passwordEncoder.encode(accountData.getPassword()));
        accountDataRepo.save(accountData);
    }

    public void removeAccountData(Long id) {
        Optional<AccountData> accountData = accountDataRepo.findById(id);
        accountDataRepo.delete(accountData.get());
    }

    public void updateAccountData(Long id, AccountData accountData) {
        Optional<AccountData> Acc = accountDataRepo.findById(id);
        if(Acc.isPresent()) {
        	accountData.setPassword(passwordEncoder.encode(accountData.getPassword()));
            accountData.setId(Acc.get().getId());
            accountDataRepo.save(accountData);
        }
    }

}
