# Build stage
FROM node:20-alpine AS build
WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy source code
COPY . .

# Build the application
RUN npm run build

# Runtime stage
FROM node:20-alpine
WORKDIR /app

# Install lightweight static file server
RUN npm install -g serve

# Copy built assets
COPY --from=build /app/dist ./dist

# Expose a default port (Railway will inject PORT at runtime)
EXPOSE 3000

# Bind to Railway's dynamic PORT
CMD ["sh", "-c", "serve -s dist -l ${PORT:-3000}"]
