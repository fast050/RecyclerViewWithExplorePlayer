package com.example.recyclerviewwithexploreplayer.data.mock

import com.example.recyclerviewwithexploreplayer.data.Enum.DataSectionType
import com.example.recyclerviewwithexploreplayer.data.model.MockModel
import com.example.recyclerviewwithexploreplayer.data.model.SubMockModel

object DataProvider {


    val image2 =
        "https://images.unsplash.com/photo-1629976002376-3bccae77037d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=880&q=80"

    val image3 =
        "https://images.unsplash.com/photo-1528709024086-98a7672e0b9d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=764&q=80"

    val image4 =
        "https://images.unsplash.com/photo-1488372759477-a7f4aa078cb6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"

    val image5 =
        "https://images.unsplash.com/photo-1566438480900-0609be27a4be?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=694&q=80"

    val image6 =
        "https://images.unsplash.com/photo-1633621412960-6df85eff8c85?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=627&q=80"

    private val subMock = SubMockModel(
        name = "khalid",
        imageUrl = image4, description = "it cool thing"
    )
    private val mock = MockModel(
        title = "Title", imageUrl = image4,
        description = "it a thing",
        list = listOf(
            subMock.copy(imageUrl = image2),
            subMock.copy(imageUrl = image3),
            subMock.copy(imageUrl = image5)
        ),
        type = DataSectionType.WideSlider.value
    )

    val listProvider = listOf(
        mock.copy(type = DataSectionType.Header.value, imageUrl = image6),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.WideSlider.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Header.value),
        mock.copy(type = DataSectionType.Header.value, imageUrl = image6),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.WideSlider.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Header.value),
        mock.copy(type = DataSectionType.Header.value, imageUrl = image6),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.WideSlider.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Header.value),
        mock.copy(type = DataSectionType.Header.value, imageUrl = image6),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.WideSlider.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Header.value),
        mock.copy(type = DataSectionType.Header.value, imageUrl = image6),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.WideSlider.value),
        mock.copy(type = DataSectionType.Category.value),
        mock.copy(type = DataSectionType.Review.value),
        mock.copy(type = DataSectionType.Header.value),
    )

}