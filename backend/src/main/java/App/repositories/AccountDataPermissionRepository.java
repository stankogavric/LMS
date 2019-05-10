package App.repositories;

import java.util.Set;

import App.models.AccountDataPermission;

public interface AccountDataPermissionRepository {
	Set<AccountDataPermission> getByAccountDataId(Long id);
}
