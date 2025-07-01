import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    host: true, // Gitpod에서는 반드시 true
    strictPort: true,
    port: 5173,
    allowedHosts: [
      // 여기에 Gitpod 도메인을 허용
      '.gitpod.io'
    ]
  }
});
