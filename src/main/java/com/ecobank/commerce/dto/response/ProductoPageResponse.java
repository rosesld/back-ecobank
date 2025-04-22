package com.ecobank.commerce.dto.response;

import java.util.List;

public class ProductoPageResponse {
    private long totalItems;
    private int totalPages;
    private int currentPage;
    private List<RegistroProductoResponse> items;

    public ProductoPageResponse(long totalItems, int totalPages, int currentPage, List<RegistroProductoResponse> items) {
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.items = items;
    }

    // Getters y Setters
    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<RegistroProductoResponse> getItems() {
        return items;
    }

    public void setItems(List<RegistroProductoResponse> items) {
        this.items = items;
    }
}
