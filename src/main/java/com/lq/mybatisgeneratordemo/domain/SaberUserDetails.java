package com.lq.mybatisgeneratordemo.domain;

import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SaberUserDetails implements UserDetails {
    private SaberUser saberUser;

    public SaberUserDetails(SaberUser saberUser) {
        this.saberUser = saberUser;
    }

    public SaberUser getSaberUser() {
        return saberUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return saberUser.getPassword();
    }

    @Override
    public String getUsername() {
        return saberUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return saberUser.getStatus() == 0;
    }
}
