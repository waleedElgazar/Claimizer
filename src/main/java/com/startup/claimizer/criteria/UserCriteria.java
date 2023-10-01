package com.startup.claimizer.criteria;

import com.waleedreda.core.criteria.BaseCriteriaFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCriteria extends BaseCriteriaFilter {
    private String name;
    private String email;
    private String mobile;
    private String password;
}
