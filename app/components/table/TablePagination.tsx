import React, { useState } from 'react'
import {  Pagination,  PaginationContent,  PaginationEllipsis,  PaginationItem,
  PaginationLink,  PaginationNext,  PaginationPrevious,} from "@/app/components/ui/pagination"
import { useSearchParams } from 'next/navigation';

export default function TablePagination({ currentPage, totalPages, onPageChange }: any) {
    const pageNumbers = Array.from({ length: totalPages }, (_, index) => index);
    
    return (
        <div>
            <Pagination>
                <PaginationContent>
                    <PaginationItem>
                        <PaginationPrevious 
                            onClick={() => onPageChange(currentPage - 1)} 
                            disabled={currentPage <= 0}
                        />
                    </PaginationItem>
                    {pageNumbers.map((page) => (
                        <PaginationItem key={page}>
                            <PaginationLink 
                                isActive={page === currentPage}
                                onClick={() => onPageChange(page)}
                            >
                                {page + 1}
                            </PaginationLink>
                        </PaginationItem>
                    ))}
                    <PaginationItem>
                        <PaginationNext 
                            onClick={() => onPageChange(currentPage + 1)} 
                            disabled={currentPage >= totalPages - 1}
                        />
                    </PaginationItem>
                </PaginationContent>
            </Pagination>
        </div>
    );
}