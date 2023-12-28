package com.startup.claimizer.specification;

import com.startup.claimizer.criteria.UserCriteria;
import com.startup.claimizer.entity.UserEntity;
import com.waleedreda.core.common.CriteriaOperatorEnum;
import com.waleedreda.core.specification.BaseSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecificationBuilder extends BaseSpecificationBuilder<UserEntity, UserCriteria> {
    @Override
    public Specification<UserEntity> getSearchSpecification(UserCriteria criteriaFilter) {
        Specification<UserEntity> userName =
                this.buildSpecification("email",criteriaFilter.getEmail(), CriteriaOperatorEnum.EQ);
        Specification<UserEntity> mobileNumber =
                this.buildSpecification("mobile",criteriaFilter.getMobile(), CriteriaOperatorEnum.EQ);
        return userName.or(mobileNumber);
    }
}
