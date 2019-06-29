package com.kirkbushman.araw.models.general

enum class SearchSorting(

    override val requiresTimePeriod: Boolean = false,
    override val sortingStr: String

) : Sorting {

    RELEVANCE(true, sortingStr = "relevance"),
    NEW(sortingStr = "new"),
    TOP(true, "top"),
    COMMENTS(true, "comments")
}