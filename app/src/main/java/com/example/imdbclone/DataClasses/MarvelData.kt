package com.example.imdbclone.DataClasses

data class MarvelData(
    val id: String, val title: String, val poster: String
)

//val marvelMovieData = listOf<ShowDetails>(
//    ShowDetails(
//        itemType = "show",
//        showType = "movie",
//        id = "164",
//        title = "Iron Man",
//        overview = "After surviving an unexpected attack in enemy territory,jet - setting industrialist Tony Stark builds a high - tech suit of armor and vows to protect the world as Iron Man.",
//        firstAirYear = null,
//        releaseYear = 2008,
//        genres = listOf(
//            GenreDetails(id = "action", name = "Action"),
//            GenreDetails(id = "adventure", name = "Adventure"),
//            GenreDetails(id = "scifi", name = "Science Fiction")
//        ),
//        creators = emptyList(),
//        directors = listOf("Jon Favreau"),
//        cast = listOf(
//            "Robert Downey Jr.",
//            " Terrence Howard",
//            " Jeff Bridges",
//            " Gwyneth Paltrow",
//            " Leslie Bibb",
//            " Shaun Toub",
//            " Faran Tahir"
//        ),
//        rating = 77,
//        runtime = 126,
//        seasonCount = null,
//        episodeCount = null,
//        imageSet = VerticalPoster(
//            verticalPoster = VerticalPosterDetails(
//                w240 = "https://cdn.movieofthenight.com/show/164/poster/vertical/en/240.jpg?Expires=1749506962&Signature=TjBVr95SxYjwKgmKf24PivyQXaB~m0UCPTsYdJYIhVrTXG1qgbDfz3HLRqgcxegV8q7MOSUxNmPmJ9C0Rvr~aNKuPJmGa1I1rOVFUV9eAUK0GIIGCJm2Dsk7JE7BC4mdGAyi~wynS6kWxV6NmVsElJn5OxD856R0PeK8SVNNGZLBcwPD-eLzPjIxdwZa8LOlYI1skuYtfoi-UsQgeJ--FfT~ypY5FYz4DQ1FLDGAVvIospokiYavR0xnbtVd0rLc1r2ZrAluWFvFO0gBoMAf4C15QCPP77OJir3MsqKygEj2ONFgQ0vC5CpixDE642s0RGk2nt-3xUa63y~nyNpPjw__&Key-Pair-Id=KK4HN3OO4AT5R",
//                w360 = " https://cdn.movieofthenight.com/show/164/poster/vertical/en/360.jpg?Expires=1749506962&Signature=CwlDuTlqaH3sbknIhW7zUyJX2quAp-Wx-qk8OeOfO70N2EeqIVYTUTT8aOybnE4Tpk3XX~zqr5KH6CZRRPbG8ZkhmROiiDYMxwWscaEonnmRZQthPWFp~swIyKPh7uk1AIAG8xTtU5aBFFOi8B~NtOKNUY3pnWlOOdnzedwOZnoXy-kB3dqq2Gb33SODALqLdwgKzg35ws5os14P4zanPN5TMxZFFArCDr4pdhBbMsHdt~d-tGBT3UcbYV77tfzkK4yrVczPQQJiLuqbSY87N842NM5zNtRUKab0b7bdbErEYnVv1IpFuja3gZd8-NBcSQphES7k1xKL6Vk7v0ePzw__&Key-Pair-Id=KK4HN3OO4AT5R",
//                w480 = "https://cdn.movieofthenight.com/show/164/poster/vertical/en/480.jpg?Expires=1749506962&Signature=hsZi7rhtiI8G0zyiqCKVIBu67Y6PlmSOnQny2gV60iCOY5cQUuXPExO5z7v4gACCIRAwTzn-vN65PfglkTyYDJJXZ19PpkTaNerDIs3FDi1VlpATNq4jDoD5Wo1X-vQk3bDg3-hUirceK57RyEBa9EaVCG97T8PQzO-AcYmJ-EhUpQO0OAaLAbFphx9zao-McJnrksWWYgVY1jdRL2vuQgMuomiD6rDY4rfk4UgKh6G4xjqQkC255l7BLoMiguyjPK4IM6zswQURvF14zhCgZZz5Q4lryUXNfZ0D3dw2bL5MToXM8USFmU~t1ylqMWXVJyeLz22GbEgt1boGNvlkJA__&Key-Pair-Id=KK4HN3OO4AT5R",
//                w600 = "https://cdn.movieofthenight.com/show/164/poster/vertical/en/600.jpg?Expires=1749506962&Signature=M4OcLrd1ejoFmnPOMxP22L~id53Egv0lcyLSSjhQ13cqA0114fjcndRon348mkzE62X2nfVfRNz-r45fX6TzUwReOQEjlO3bza9Zxeo2ZcJmUdnHW7~4hptnyu~UB2Ub-claq56qPT2lZm0bZn8AMHOTKHqqnlM2k9T8NCRI-UbSfqKn31mOG0QvTPAwtYR5mFPB3wXq~vmrWza-MplE1QGNzOtW7hGjmiqXXtmXMRP-9gEbYOSfMOSMMMhnKugsgWg1yoEtuCs0923k41efXVqOIeB0c86bzvSFtPDdhhDnajdwQDKH4cyr63GtTZP39EQfYY~YgIR~H7lPR6i6Bg__&Key-Pair-Id=KK4HN3OO4AT5R",
//                w720 = "https://cdn.movieofthenight.com/show/164/poster/vertical/en/720.jpg?Expires=1749506962&Signature=DMN~p5TZt5Ai~F397alcNyxWyt4bjVD9ttn62MfM~yOWZQH09I9iuy23gi6gzuNtKlD5kiEK58u~sIsU~i402wo93TTfArg6kaYG8rqHXg2ZkOzZjU2NBnYdTrMxqoxAQtKKqPU~UHdH88Cq9yObg64OhiZJPSh~DFMC5p4pa9zl9xT1PmxfXaN1IrDjp3mcaM4xmYkUasyOPjFhsYLbDMzDlWX9hbJxzR0Aj5I3egWR32gsqk90qf2NJMJAtyds4XKqqaJhSx6IJDh0HgeToaHm87rZQDO-b1se5Qw7-Sl7SP7Y82-LTim-XJVWMpPyBAa0mG5zap7ew4i2dULihA__&Key-Pair-Id=KK4HN3OO4AT5R"
//            ),
//            horizontalPoster = HorizontalPosterDetails(
//                w360 = " https://cdn.movieofthenight.com/show/164/poster/horizontal/en/360.jpg?Expires=1749506964&Signature=Oa2p2eEvYmcCB196MD72Ac8-aJB901mh0wiqBJjqmFjzDe4pDbYkt8lwLmYRIHFvdXlnhLUrQir-L11bjNbIZU1GxCqc021pkutuQwmeHOFxVT31yTUJTfOJ65SMdSQF-izngb0jemZilBnmFZL5~fD0~pAvMANlOtQpvXE31zZPDtvlb6V~ffIZLZeqb0lhmi0sNWmRhv1OT~RUiMCLAcTrH0wel3YNrqGyM3VTlewjulouMq6hIzfWz2evObvIiAg2uCVfq1GV1UEmuJuP8IJb9YH0n9pvpfqbmcx6Rpt~5uSJJMEiBzgwfjRgAFSnRsc2Pjybzx62k2UGGBO0gg__&Key-Pair-Id=KK4HN3OO4AT5R",
//                w480 = "https://cdn.movieofthenight.com/show/164/poster/horizontal/en/480.jpg?Expires=1749506964&Signature=I5R~wDp~fpRXayTYBleC4tdz42ZWAjLuKqsKwIhbLYL3u0VIJoaZSGtLrJFX39JVBA7nwf9oDu9gE6zx72vTvqPUKweZJAkt~uxqEmn37g7UEcsY2zHk9bVVs4E6dM48IMm~KpQTY-MZn9X07EeGtUJRqN3d-reTgEIibSaMIAJv2G9rAQL5NZpiGloNEWLT2d~LVfWkdMwycMb6Kc3px~9-8ZrHI0XEM3QwDfwzUc7ztgkBR9eZZiB0aHg2ug2gW3wqQMx6gp26PJyzkzFPS-x7AR8bWde7w7NwAkRCFlTds-mEgiug3RF43P6hy13wdEMHx4u3MLZXN0HUjp6vtQ__&Key-Pair-Id=KK4HN3OO4AT5R",
//                "",
//                "",
//                ""
//            )))
//














            val MarvelList = listOf (MarvelData(
            "164",
            "Iron Man",
            "https://cdn.movieofthenight.com/show/164/poster/vertical/en/360.jpg?Expires=1749506962&Signature=CwlDuTlqaH3sbknIhW7zUyJX2quAp-Wx-qk8OeOfO70N2EeqIVYTUTT8aOybnE4Tpk3XX~zqr5KH6CZRRPbG8ZkhmROiiDYMxwWscaEonnmRZQthPWFp~swIyKPh7uk1AIAG8xTtU5aBFFOi8B~NtOKNUY3pnWlOOdnzedwOZnoXy-kB3dqq2Gb33SODALqLdwgKzg35ws5os14P4zanPN5TMxZFFArCDr4pdhBbMsHdt~d-tGBT3UcbYV77tfzkK4yrVczPQQJiLuqbSY87N842NM5zNtRUKab0b7bdbErEYnVv1IpFuja3gZd8-NBcSQphES7k1xKL6Vk7v0ePzw__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "638",
            "The Incredible Hulk",
            "https://cdn.movieofthenight.com/show/638/poster/vertical/en/360.jpg?Expires=1749506297&Signature=jjQtW42~OPQq7lUJZmS8~JvfD5bdckE7Sa8cnOEOMc3b4K8BUMBwdDIsgpBCM1g4EzqgsJ7Qop6e7pO0Q-XgiU9yr-q895jGDImeNh91kBbIolSg42kushrV89ylaJCi4AbnNhzDHDKfQgHxfNF7yJCxCyiaqZOEpQRXaR7zt1sfWx8Pk3PL7125IxWMtLNgdRT~g6fKgyk6adeUq6GS8DcdWMpg5NEQHHn1Cbjar2wgcVJYN7hnjKCulzKzscJbkovZCc9c3zaHt18Bth88tja0NQm927cRT~HaK~2DB73KJ1zZNKBfNtdchrEflYndbA2bbp9UDRM3LH20CITuxg__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "262",
            "Iron Man 2",
            "https://cdn.movieofthenight.com/show/262/poster/vertical/en/360.jpg?Expires=1749509277&Signature=ZObzMx51H5lgZlzOkNANAj~KD29CQclzJfwDl6u80Mtyo0hph6owY102QQKEShcFIwJMtLuqti61S2-Qe3Mhq6XZkrSJp0rpINmoWWAIIP9OHu-hrlp~Hh0nhR6qJWhqz4WXqkLAOXfiaC9VZSkiCFFqiCcEA1mTnXohuobYYHHNb5fscsza8EN4iloxzc5szUcGAvBukvTLx8aUoti4jNjED6ED2Gqyb6SFYMBgzjapf7KF114pKYw~rtmype2MPpjMBgw4q3mPcgblWKYHE6AhUCqMbN7nGfKycTaVAv6AAQIXsRxKtycb81lQ5FJYEJOUOWT4IBgjmbgCbD1lew__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "233",
            "Thor",
            "https://cdn.movieofthenight.com/show/233/poster/vertical/en/360.jpg?Expires=1749503347&Signature=Mlwb4EWN1G-QTjtV4Xp83WTPfLRKCK45bD1LWdm0jowiiNl9LEqTLmGGH6EWc91Llqn0b-cESIr9hBE2VfqpCAI6~~0SC3Ayg7zfK6p8TAYVO4ZIEZymVBHYk7FG2axQMAhjxWefl8nzDNACnM3yn~AsvdjaudOkYpwfZn~eL6SmS~JaWWaFMdOhexeuzC9eURw9oSdJJiZH2ymDf8Bkqol5Hxgii3Sw2zU~KjXsbMPhbD3dKKPRscpBp5g6WLdFNRCNu8163M6I8aMLDFDvyBzfoFqkH3iN0y7UVmlShKalz6NKwATOf-FjL1m8csNE3wbR5lXP-1PL6RYDAuiXjw__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "237",
            "Captain America: The First Avenger",
            "https://cdn.movieofthenight.com/show/237/poster/vertical/en/360.jpg?Expires=1749503369&Signature=iD1zkNHP2Mw7~X5nRF7LJiFdnMxBqQTDAwirvz5-Al4uaoXeWDcCzsfQPgM4XOEwla3R-MB3Mom82jYMkDyvI17By3kzse~91g2mqccdh8rBQ~eVk8rADs8DD8kl9STOIEGMNKpLUbVzyGsRF-Jo396geTeNqD-ZLRFNeyBcBnP1qCq--V51xD50rP5G7CLkycZBYs-upW2ETtXQ3198q0rR45iEAwkDn9LtpJeZb0w4e1ikyGlDPfxCDalpCWWICH4oj49yZAZkssQIYgRXdpGXUiHTAtg8PoZYEviQs0wbzCP9oGxr3KMQ6v7bYbVEEDjasnshs~B5XuiCAXhMJQ__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "108",
            "The Avengers",
            "https://cdn.movieofthenight.com/show/108/poster/vertical/en/360.jpg?Expires=1749514908&Signature=ODGtWPxl5PPgSY0MCs7qs4ihfdGfrmBnKTit4SSk4Zl~V1zGj9n5TPHh6zDFUq7e~MrLCv5nsHfvgXmZ-YdYK9xCahjiZR-1JfD4shintJ-iAm6ll~XZvciB-Aqor~PWSCij8~2y1XdOMn-KgF09t365BspfWkgHY8OlWDoWUR1Ebar4i3wLqwpKr~An2bkTWrt7zlWFNblFQwu~6Auc5fymwMONeLjMLXGrfOvJjLv5GvEk-i24ABk~68QLV3877Gfe1kTZQeqhJmwSQ6MdksRZiXP99PhtA4VLXQJ7p5y4S42VbT2SjAsGk~vOC8ie2xYnGBs1iT9pKR5kXqWnBA__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "231",
            "Iron Man 3",
            "https://cdn.movieofthenight.com/show/231/poster/vertical/en/360.jpg?Expires=1749509432&Signature=i7ZLujQSFcq0LK1TLBg4OY4sii-ewaNGm~H96yhv5F468k10Zrw0353A27RhcP~oX-OJR32H5YMcRidusT4c8GLW8Q1bShHCQITCn74kClmWAJSXCCfgr4YIU3q4j-VKNCNADKV9CFMkdidaXT91BDlx1HF8FbWGAhTsjyHXXFLTSgyisqm0Z~rP3XPYDDDOkkHdm1wlTDyebPVnlM9FoSbaYsXYveUNmIYBlhgEM39Db1sspY-XMxQSCzVT5A42tJUPupk4sswgK0EQADGjA~cPPr4-jxVOQdq8eE0ojWAImmlfuYrMbfwGqKAiLJmbHG8vQkNQ8pNzXGcf~rHKrw__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "365",
            "Thor: The Dark World",
            "https://cdn.movieofthenight.com/show/365/poster/vertical/en/360.jpg?Expires=1749514805&Signature=MDNviMYYbdI-Jyqf-5Qq7DIheQMaHSkL4Pilz1KKU4umOLOjlRnn-A7DFdJlDq5VqbHI4s73X2NvF7xzzRrE7vgsZ~f6TRRO8DyJ258FdlTTWyVuTkPWrNw2Us0FKdkJdbRhwGhROM7A2LU5HGLy1viA2-XnE4SoO1ffN8srSfQIHWnCJ-xg6q3jZsBoho7ES65ZFobLJhhlyMMlNodjOg46kDcOIO~jvv2OUJ6uBYzG8KgI6lxxnx1oKExOh8SSOa9N0VM8h3yVowElC9V-70Xw7qjqZD5HF8Pu3I8~Z8~bIycMWztaU39bdzigyeF6i2adY9LRinrsR9C5aBUP8Q__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "234",
            "Captain America: The Winter Soldier",
            "https://cdn.movieofthenight.com/show/234/poster/vertical/en/360.jpg?Expires=1749503338&Signature=ZZWAi~kjWmeQYoizqtmp0hjNyIygmRfM8esLS8-4WGh3p2sVYXCX5OjJi6n8BmR~AV9DcGcxQWtZ81e0tnY7Mu5dFJetnI1~rb434uEPwnixnmnnr4dt~3zadP1gcNF06k1kL-IVwubp1X9WJRvpJu8ugc7juhAXT2fREXDplyV3vdaNiDvYDFklZv7tXd8~QLAPIuonLI42SdlDdL770pulxu45dA~74wxb5jl0d7nJ97atNlvRPbtR5Eud53o~ek9aYWPQt~2TEtPSBnKlGPL8bTGVRLy7iIGVu4--1Bb~2cADMCFBLm2PFflgqs3WWeDjF2~aAwQCFhluk5FsqA__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "133",
            "Guardians of the Galaxy",
            "https://cdn.movieofthenight.com/show/133/poster/vertical/en/360.jpg?Expires=1749507534&Signature=Rstvetxyx9dpWPyHOqZsJ8ysllDeuC0TqtGCjfcwIN35NAGI~q4aKwBosbwKbFZWdfOPz8k-rTqIuDmyWYi1H~fxR6at2s5z6KAkWtB8f0XZu22QU8vB~korRv~502QcO3N4o0FKg9ZBJRKX6PR-4MWx1n8AvprhvCVKP~CVrGJL4TblVAr-cgs-Q8CuDeDY8sd~wkmf4Bq1PdIpk1CrB6d0FdR1ERX~77oFT0ikOX1czro7O8oCwG3qMRa-pSkHgsnvyXoBMAvFQdkmLduEHXmx9ZlGFM-cdGcLz8FK5oih6qM6bRugKNwnwwLX1N1xvPFlZA5U1rzR1x13ndfipA__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),

        MarvelData(
            "224",
            "Avengers: Age of Ultron",
            "https://cdn.movieofthenight.com/show/224/poster/vertical/en/360.jpg?Expires=1749503311&Signature=fNC1iJGQ1Ia0K9KVyF3ZyQJ8AkFEH6MrJR6uFSEG1NkTdLOfrSOJUAV3-vBCdjyFTNfvmpUZRXDEg54pYw~mvYJmzUHPO5QTGfDp1S6UZ-0z6ZgW1IBKe-8YPIFdyCPl08BKqw~A4eH2LZo~1J44U2NexusQJbbhKvvw8IEuLRdEg47Zvf7G5Q7weZlgxNViIgDxluFbuqstICZkCmWPvNp7Hz8N4fGiT5FRfn8bfxwMxC5qOSPxKJu3SFxleT~8mJAmMCyMYXrjUq-5kJnQy26RZUHSJZtR0ZfhxY801a9zKhCz0iVaXh6HeuSLr389Yt6tW-lMtLLI0Prc~X4bOQ__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "387",
            "Ant-Man",
            "https://cdn.movieofthenight.com/show/387/poster/vertical/en/360.jpg?Expires=1749503477&Signature=UKfX-QwezzzZ1MGY2Qm2jMwa2LPleD5cItMvMR69ALD46Lef7USh684Ed9q~-csedw2DRP0Wll7037F20MFEpGSQiKV1tHOE5ROmgLuBtPtns1evGQ~XoetAHfrKxchio24M7La4g8bg7PmYLEb81A7ZPCQuly5ga5DxHbgHn9rBjI9RVGfwNUfmxT-SKBK2ei8zWrRY1RNNvDHnSgUk0IbIXg-ijjdxoytymmgHhYzX5xRe6WsPj06R2I-P2aUlNZYA-eYL0UOZ99cY~Y3fzW79fydtTKy~027i8M-Y6Pt8IA3IbeHGCar74sNU995~ZoFhJO6~GgZZm-Dppzxv3g__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),

        MarvelData(
            "279",
            "Captain America: Civil War",
            "https://cdn.movieofthenight.com/show/279/poster/vertical/en/360.jpg?Expires=1749509395&Signature=ZETfI7VV4X0sP6lVI1qBS10jU4tttFdaZm6o1F9qHd-ghRqilJGU2JHAc6vDsqfkpgvaTOlTGBdN7XOQTEIqDowK4OERTA7HNDxwz3pGIKBaS~s0QTDzQuYjJmhef3Xr8xohi1onNr~cx3udpXoUFTZ3frVWfIDxv75-QXel4OalPHLj0fwFTk7U7nAoQjl43FtirUXPMSB8IJzfxj1kaji-vARbMko~WN5FqN8PxR-0wgjeN~UdvDpGxD8WwkWTJbRP9susf53NzomjApcfG8HMyEnFPQjmtik1ejkyT2AclHi6MLg-kuo4pWmf5-1ZY9hE4lILnbpjFSJ1dKrFQw__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "318",
            "Doctor Strange",
            "https://cdn.movieofthenight.com/show/318/poster/vertical/en/360.jpg?Expires=1749522349&Signature=JP8ndMAmahlVQ~xF2udDWOffKTV6BTr5Ob0YMcZxgaIghyVSyfz-p2yQmqhoMfG606pYpQesOwd8plpRHs8Gln2KNQpuHNdsuWwZczf8qPt9ckv7ji~cmezLiZUMEbWv72d8yBNyP~vbtC81-GyJ-7KyVRc-6IOxLjXYv840L68MlhUE1iu2613CAl3cWnOiHuLuTlEpPrTN9gYVwbGDMU81OPbC2es9MdAhrZI6zGC5k18sUDjo4pwlHs2p3PyrvWhcBHH7~OwedkRH9UzoAR0HrIaHLrQJ9bYEewf6a32sVFjecCGcP2IfVyjOWkixJS6777lnKSyTcy2JsbFsnA__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),

        MarvelData(
            "366",
            "Guardians of the Galaxy Vol. 2",
            "https://cdn.movieofthenight.com/show/366/poster/vertical/en/360.jpg?Expires=1749503438&Signature=ferxplKFkUZI0mTjHgziGGEkI7b3vWLK8N0Ah6BidxzCC3fI2pckPnrmdV4DbHbeYPAkQcwGe~C41xCaNM5kRAF3KrP508l7agYdtAvF8UBWqPMGzjRWKq~6HRlFITsVymSMtBKr-o~TYfdekczHG9XCXh99thf1M8Mvpf6sO8TeVRFfHzaQkvey2vRYQ3PIjyxFwo8eZMMRnpXutOPzk4Ic-A0i11GoIxjPu51iZI~8clChEtI9k09BURXX5sLXfx0B0Lp1MWK3-O2sJqMn-emayWYFqZen-3Gxrd2pVL6p4~fVR6eXcEy7jbDADPD-zKS0O5WvSvVejifNzc2y2g__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),

        MarvelData(
            "315",
            "Thor: Ragnarok",
            "https://cdn.movieofthenight.com/show/315/poster/vertical/en/360.jpg?Expires=1749508814&Signature=HA9FGqNgngZ2ZFyT8rE5~jkDtQ5npsPrvpNQBAbAAHrKLvvfpFMOjfq3aw8ZQjGRo4KjZzwAPm2YDfw3J7PnA8VNdYoouOdZd3ef8FFxgjtfvj2I0kIQTAD-kBP9IdfinO3BpL0Fut6FAHYc9U0IG2YTyVbxMXAxJFpFMrqo5QJPqESFUcESMayEpAHnOF8hs8z~KRyv6sgoO24liq0lIBzNNPw2SHSSCWLnjxKS0G23onR5l8atnH~mPGg8WsQm~FxfdyOWkDK5Bf-08ZVm8jY-u0~7L4OU-yxfyTLBHCX5wc1dySx3w9LGfY5KWFPvWFOF18k5JoBfuySNgMGsNw__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),

        MarvelData(
            "286",
            "Black Panther",
            "https://cdn.movieofthenight.com/show/286/poster/vertical/en/360.jpg?Expires=1749709037&Signature=LYrEwk1wGeR0vuuO98MzpJMIMlFeodgh-DuoICocClQGR~JJC8F5ppZgjtNDkjobwhSrBdkhssyTdwSf4ziBMMlLxYRhz89rCRPhbxsYZGA1GIIWahf8nhbZK6dKO-YXEwr7X3qJq96bPF7jLfgZ~Vws~zP1NGG9uSgTtNNUPxRi4RIlVZx5YLIizNhEaJp7M6sfrjGWH37zb3aF1ysQ-79qr8K9F7aDzaJqn5IoQKv~sGIRkXeYxF43FrZ1pSGEiloFJCiNLsQKepLnte9WfkOYMrE8z2mfPw~EzFjXgOq6mhdAbXwzhKnKsc6350rLoCZNXkC4RPPVNtMdLTmuig__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "162",
            "Avengers: Infinity War",
            "https://cdn.movieofthenight.com/show/162/poster/vertical/en/360.jpg?Expires=1749509116&Signature=TqR7Z67-VvSrkYxAIdsft1uZI642mPfex5FRxjufoXq7rRt~mAE1Xm57v8fVLMrqxHhMFCHtpJsh9d8QDcWK9ggvdIXxsWqcyklEl4CxLdhEtXDKeIw~hTTeLFbP08XRi-J069WM0pzyC-Ajv4NQqeN1Yj594VDaJJ83oU9aKpKjAhJ4YY6txiOyPeLycVdNAdSm7XPtv9ERHUnlDOgB6YLcpJyW5MUPWJpHk-RRjyTMnlsR6RKxRYbux5cILyBAuMU5vZxbVajoDtbHODXIbEhlCIqsrqcKiHtvxtnfp5z5mwnvqwhTmZDQXmXuuEd0hEuvYCGuFct74iRXjBRfpw__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "529",
            "Captain Marvel",
            "https://cdn.movieofthenight.com/show/529/poster/vertical/en/360.jpg?Expires=1749507331&Signature=jb7KBcBEb4Ru04ww0u77clvy5mkzg2CX2lEXiyJhCJDGqdTkgQkbuCcXGNhfa8cckY38~k9xGWDwpsSeCfYmJFiYIIVjOnoR8oOFZpJO5sWARFjvQY35Thtyuthh0gm8gNKx3o-qDyeqvImr6XCSxftFQ9E6Xxrcf45sBXIpXE6fSfc6iAMuQPQdIGcB-dSBM~E5xdH6z6OvMFLL6JApToyKCeiX7RqcFzTuleUKZSYaW3M0j9UkipX1m~udYqW-M-QfvSt7d61Ed5h7CAREDIJ-U-PLsynTD06KhFPFP8YxaSW7haWkooQwjCMtC1tDI4VG1XROhcuH299o1Htu~A__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),
        MarvelData(
            "146",
            "Avengers: Endgame",
            "https://cdn.movieofthenight.com/show/146/poster/vertical/en/360.jpg?Expires=1749733712&Signature=f9QwwGNiwoojar-MM7SZ0-hoORUU1cLRGMJ0LMUqvnGaygRXq178vAeTsZ-rS-DTyhbH-HWNKs0IVYz2fq6BjeCGmcfiKMm~8M-eqFsRt5TJ5gJg5S9Cu3sNLcnPER-vakQEc0~b1ScQ4s95Gb2ZXTbYlBoOY9K7IAgYogGZbTH8UBV-1JWRQRJjWN4bT8mEjlJKLcn~YcotqSZEOlfQvQtdXbsFUSZW1ZZ6D-z76C-kPXMIYfn7LRSoXt9wUSw9DSl7cCtpQBpMmIVFaSCwBYDIGKAwAgFFxbA-p4o-6gvAtza-riinO2pdzSQEAsj20bXAmTXg5SMAYs4tHkOahA__&Key-Pair-Id=KK4HN3OO4AT5R"
        ),

        )
