import React from "react";
import { Link } from "react-router-dom";

export const Layout: React.FC<{ children: React.ReactNode }> = ({ children }) => (
  <main style={{ padding: "1rem" }}>
    {children}
  </main>
);

