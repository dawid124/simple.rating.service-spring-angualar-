export const EMAIL_PATTERN = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

export const environment = {
  production: false,
  SERVER_ADDRESS: 'http://localhost:8080',
  JWT_TOKEN: 'jwt_token',
  ACCESS_TOKEN_PARAM: 'Authorization',
};

export const api = {
  AUTH: {
    LOGIN: '/auth/login',
    REGISTRATION: '/auth/register',
    AUTHENTICATE: '/auth/authenticate'
  }
};

