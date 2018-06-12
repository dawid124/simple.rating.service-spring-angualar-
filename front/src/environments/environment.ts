export const EMAIL_PATTERN = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

export const environment = {
  production: false,
  SERVER_ADDRESS: 'http://localhost:8080',
  JWT_TOKEN: 'jwt_token',
  ACCESS_TOKEN_PARAM: 'Authorization',
  INFINITY_SCROLL_LIMIT: 30
};

export const api = {
  AUTH: {
    LOGIN: '/auth/login',
    REGISTRATION: '/auth/register',
    AUTHENTICATE: '/auth/authenticate'
  },
  PRODUCT: {
    NEW_PRODUCT: '/product',
    PRODUCT: '/public/product/',
    PRODUCTS: '/public/products/',
    NEW_TYPE: '/type',
    ALL_TYPES: '/types',
    IMAGE_CONTROLLER: '/image/',
  },
  RATING: {
    ALL_RATING: '/ratings',
    CREATE_RATING: '/rating'
  },
  COMMENT: {
    CREATE_COMMENT: '/comment'
  }
};

