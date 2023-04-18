import React, {  Fragment,useEffect,useState } from 'react';
import Button from 'react-bootstrap/Button';

const Pagination = (props) => {
    const { totalRecords = null, pageLimit = 30, pageNeighbours = 0 } = props;

    let limit = typeof pageLimit === 'number' ? pageLimit : 30;
    let records = typeof totalRecords === 'number' ? totalRecords : 0;

    let neighbours = typeof pageNeighbours === 'number'
      ? Math.max(0, Math.min(pageNeighbours, 2))
      : 0;

    let totalPages = Math.ceil(records / limit);

    const [currentPage,setCurrentPage] = useState(1);

    const LEFT_PAGE = 'LEFT';
    const RIGHT_PAGE = 'RIGHT';

    useEffect(()=>{
        gotoPage(1);
    })
/**
 * Helper method for creating a range of numbers
 * range(1, 5) => [1, 2, 3, 4, 5]
 */
    const range = (from, to, step = 1) => {
    let i = from;
    const range = [];

    while (i <= to) {
        range.push(i);
        i += step;
    }

    return range;
    }

    const fetchPageNumbers = () => {
        const totalPage = totalPages;
        const currentPag = currentPage;
        const pageNeighbours = neighbours;
    
        /**
         * totalNumbers: the total page numbers to show on the control
         * totalBlocks: totalNumbers + 2 to cover for the left(<) and right(>) controls
         */
        const totalNumbers = (neighbours * 2) + 3;
        const totalBlocks = totalNumbers + 2;
    
        if (totalPages > totalBlocks) {
          const startPage = Math.max(2, currentPag - pageNeighbours);
          const endPage = Math.min(totalPages - 1, currentPag + pageNeighbours);
          let pages = range(startPage, endPage);
    
          /**
           * hasLeftSpill: has hidden pages to the left
           * hasRightSpill: has hidden pages to the right
           * spillOffset: number of hidden pages either to the left or to the right
           */
          const hasLeftSpill = startPage > 2;
          const hasRightSpill = (totalPages - endPage) > 1;
          const spillOffset = totalNumbers - (pages.length + 1);
    
          switch (true) {
            // handle: (1) < {5 6} [7] {8 9} (10)
            case (hasLeftSpill && !hasRightSpill): {
              const extraPages = range(startPage - spillOffset, startPage - 1);
              pages = [LEFT_PAGE, ...extraPages, ...pages];
              break;
            }
    
            // handle: (1) {2 3} [4] {5 6} > (10)
            case (!hasLeftSpill && hasRightSpill): {
              const extraPages = range(endPage + 1, endPage + spillOffset);
              pages = [...pages, ...extraPages, RIGHT_PAGE];
              break;
            }
    
            // handle: (1) < {4 5} [6] {7 8} > (10)
            case (hasLeftSpill && hasRightSpill):
            default: {
              pages = [LEFT_PAGE, ...pages, RIGHT_PAGE];
              break;
            }
          }
    
          return [1, ...pages, totalPage];
        }
    
        return range(1, totalPage);
      }


    const gotoPage = (page) => {
    const { onPageChanged = f => f } = props;
    const currentPage = Math.max(0, Math.min(page, totalPages));
    const paginationData = {
        currentPage,
        totalPages: totalPages,
        pageLimit: limit,
        totalRecords: records
        };
    setCurrentPage(() => onPageChanged(paginationData));
    }

    const handleClick = (page) => event => {
        event.preventDefault();
        gotoPage(page);
      }
    
    const handleMoveLeft = (event) => {
        event.preventDefault();
        gotoPage(currentPage - (neighbours * 2) - 1);
    }
    
    const handleMoveRight = event => {
        event.preventDefault();
        gotoPage(currentPage + (neighbours * 2) + 1);
    }

    
    

    if (!records || limit === 1) return null;
    const pages = fetchPageNumbers();

    return (
      <Fragment>
        <nav aria-label="Countries Pagination">
          <ul className="pagination">
            { pages.map((page, index) => {

              if (page === LEFT_PAGE) return (
                <li key={index} className="page-item">
                  <Button className="page-link" aria-label="Previous" onClick={handleMoveLeft}>
                    <span aria-hidden="true">&laquo;</span>
                    <span className="sr-only">Previous</span>
                  </Button>
                </li>
              );

              if (page === RIGHT_PAGE) return (
                <li key={index} className="page-item">
                  <Button className="page-link" aria-label="Next" onClick={handleMoveRight}>
                    <span aria-hidden="true">&raquo;</span>
                    <span className="sr-only">Next</span>
                  </Button>
                </li>
              );

              return (
                <li key={index} className={`page-item${ currentPage === page ? ' active' : ''}`}>
                  <Button className="page-link" href="#" onClick={handleClick(page) }>{ page }</Button>
                </li>
              );

            }) }

          </ul>
        </nav>
      </Fragment>
    );
}
export default Pagination;