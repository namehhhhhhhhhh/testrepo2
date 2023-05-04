package com.dotseven.poc.user;


import java.util.Objects;

    public class User {

        private Long id;
        private String username;
        private String password;

        public User() {
        }

        public User(Long id, String username, String password) {

            this.username = username;
            this.password = password;
            this.id = id;
        }

        public User(String username, String password) {

            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + Objects.hashCode(this.username);
            hash = 79 * hash + Objects.hashCode(this.password);
            hash = 79 * hash + Objects.hashCode(this.id);
            return hash;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("City{");
            sb.append("username: '").append(username).append('\'');
            sb.append("username: '").append(password).append('\'');
            sb.append(", password: ").append(id);
            sb.append('}');
            return sb.toString();
        }


        public boolean equals(User user) {
            return this.password.equals(user.password) &&
                    this.username.equals(user.username);
        }
}
