package com.example.lesamobile.Models;

public class LoginDTO {

    private String token;
    private Usuario usuarioInfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuarioInfo() {
        return usuarioInfo;
    }

    public void setUsuarioInfo(Usuario usuarioInfo) {
        this.usuarioInfo = usuarioInfo;
    }

    public static class Usuario {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
